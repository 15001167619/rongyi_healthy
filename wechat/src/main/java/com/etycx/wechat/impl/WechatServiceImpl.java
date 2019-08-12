package com.etycx.wechat.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.etycx.remote.entity.HealthyOrder;
import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.IAuthUserService;
import com.etycx.remote.service.ISysUserService;
import com.etycx.remote.service.IWechatService;
import com.etycx.wechat.common.constant.WechatConstants;
import com.etycx.wechat.common.utils.DateUtils;
import com.etycx.wechat.common.utils.MapUtils;
import com.etycx.wechat.common.utils.XmlUtil;
import com.etycx.wechat.common.utils.wechat.WechatRefundApiResult;
import com.etycx.wechat.common.utils.wechat.WechatUtil;
import com.etycx.wechat.config.WxConfiguration;
import com.kuding.anno.ExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * <p>
 *      微信管理 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
@ExceptionListener
public class WechatServiceImpl implements IWechatService {

    @Reference(version = "1.0.0",check = false)
    private IAuthUserService authUserService;

    @Reference(version = "1.0.0",check = false)
    private ISysUserService sysUserService;

    private final BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo userInfo(Integer userType, String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        WxMaService wxService = checkUserInfo(userType, sessionKey, signature, rawData);
        if(wxService == null){
            return baseVo.error("user check failed");
        }
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        if(userInfo!=null){
            BiConsumer<WxMaUserInfo,Integer> initUserInfoFun = new WechatServiceImpl() :: initUserInfo;
            initUserInfoFun.accept(userInfo,userType);
            Map<String,Object> params = new HashMap<>(1);
            params.put("credenceUnique",userInfo.getOpenId());
            BaseVo tokenData = authUserService.createAuthenticationToken(params);
            if( tokenData != null && tokenData.getError() == 0){
                Map<String,Object> returnMap = new HashMap<>(2);
                returnMap.put("tokenData",tokenData.getData());
                returnMap.put("userData",userInfo);
                return baseVo.ok(returnMap,"解密用户信息成功");
            }
        }
        return baseVo.ok(null,"解密用户信息失败");
    }

    private void initUserInfo(WxMaUserInfo wxMaUserInfo, Integer userType) {
        sysUserService.saveWechatUser(wxMaUserInfo,userType);
    }


    @Override
    public BaseVo phone(Integer userType, String sessionKey, String signature, String rawData, String encryptedData, String iv, String openId) {
        WxMaService wxService = checkUserInfo(userType, sessionKey, signature, rawData);
        if(wxService == null){
            return baseVo.error("user check failed");
        }
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        //会员信息手机号录入
        try {
            sysUserService.initWechatUserPhone(openId,phoneNoInfo.getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVo.ok(phoneNoInfo,"获取手机号成功");
    }

    @Override
    public BaseVo prepay(Integer orderId) {
        //查询 通过订单号查询
        HealthyOrder orderInfo = new HealthyOrder();
        String nonceStr = RandomStringUtils.randomAlphanumeric(32).toUpperCase();
        Map<Object, Object> resultObj = new HashMap<>(10);
        try {
            Map<Object, Object> params = new TreeMap<>();
            // 小程序 appId
            params.put("appid", WxConfiguration.getPayService().get("appId"));
            // 商家账号
            params.put("mch_id", WxConfiguration.getPayService().get("mchId"));
            // 随机字符串
            params.put("nonce_str", RandomStringUtils.randomAlphanumeric(18).toUpperCase());
            // 商户订单编号
            params.put("out_trade_no", orderInfo.getOrderNo());
            // 商品描述
            params.put("body", "体检套餐");
            //支付金额
            params.put("total_fee", new BigDecimal(orderInfo.getPrice()).multiply(new BigDecimal(100)).intValue());
            // 回调地址
            params.put("notify_url", WxConfiguration.getPayService().get("notifyUrl"));
            // 交易类型
            params.put("trade_type", WxConfiguration.getPayService().get("tradeType"));
            // 支付 openId
            params.put("openid", orderInfo.getUserId());
            String sign = WechatUtil.arraySign(params, (String) WxConfiguration.getPayService().get("paySignKey"));
            // 数字签证
            params.put("sign", sign);
            String xml = MapUtils.convertMap2Xml(params);
            log.info("xml:" + xml);
            Map<String, Object> resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce((String) WxConfiguration.getPayService().get("uniformorder"), xml));
            // 响应报文
            String returnCode = MapUtils.getString("return_code", resultUn);
            String returnMsg = MapUtils.getString("return_msg", resultUn);
            //
            if (WechatConstants.PAY_FAIL.equalsIgnoreCase(returnCode)) {
                return baseVo.error("支付失败," + returnMsg);
            } else if (WechatConstants.PAY_SUCCESS.equalsIgnoreCase(returnCode)) {
                // 返回数据
                String resultCode = MapUtils.getString("result_code", resultUn);
                String errCodeDes = MapUtils.getString("err_code_des", resultUn);
                if (WechatConstants.PAY_FAIL.equalsIgnoreCase(resultCode)) {
                    return baseVo.error("支付失败," + errCodeDes);
                } else if (WechatConstants.PAY_SUCCESS.equalsIgnoreCase(resultCode)) {
                    resultObj.put("appId", WxConfiguration.getPayService().get("appId"));
                    resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + MapUtils.getString("prepay_id", resultUn));
                    resultObj.put("signType", "MD5");
                    String paySign = WechatUtil.arraySign(resultObj, (String) WxConfiguration.getPayService().get("paySignKey"));
                    resultObj.put("paySign", paySign);
                    return baseVo.ok(resultObj, "微信统一订单下单成功" );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return baseVo.error("下单失败,error=" + e.getMessage());
        }
        return baseVo.error("下单失败");
    }

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String responseXml = new String(out.toByteArray(), "utf-8");
            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(responseXml, WechatRefundApiResult.class);
            String resultCode = result.getResult_code();
            if (WechatConstants.PAY_FAIL.equalsIgnoreCase(resultCode)) {
                //订单编号
                String outTradeNo = result.getOut_trade_no();
                log.error("订单" + outTradeNo + "支付失败");
                response.getWriter().write(setXml(WechatConstants.PAY_SUCCESS, "OK"));
            } else if (WechatConstants.PAY_SUCCESS.equalsIgnoreCase(resultCode)) {
                //订单编号
                String outTradeNo = result.getOut_trade_no();
                log.error("订单" + outTradeNo + "支付成功");
                // 业务处理 更新订单
                response.getWriter().write(setXml(WechatConstants.PAY_SUCCESS, "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * <p>
     *      返回微信服务
     * </p>
     */
    private static String setXml(String returnCode, String returnMsg) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
    }


    /**
     * <p>
     *      获取微信AppId
     * </p>
     */
    private String getAppId(Integer userType){
        return userType ==0 ? WechatConstants.MIN_APP_0 : WechatConstants.MIN_APP_1;
    }

    /**
     * <p>
     *      用户信息校验
     * </p>
     */
    private WxMaService checkUserInfo(Integer userType,String sessionKey, String signature, String rawData){
        Function<Integer,String> appIdFunction = new WechatServiceImpl() :: getAppId;
        String appId = appIdFunction.apply(userType);
        final WxMaService wxService = WxConfiguration.getMaService(appId);
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return null;
        }
        return wxService;
    }



}
