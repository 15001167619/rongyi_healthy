package com.etycx.wechat.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.IAuthUserService;
import com.etycx.remote.service.IWechatService;
import com.etycx.wechat.common.constant.WechatConstants;
import com.etycx.wechat.config.WxConfiguration;
import com.kuding.anno.ExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
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

    private final BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo userInfo(Integer userType, String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        WxMaService wxService = checkUserInfo(userType, sessionKey, signature, rawData);
        if(wxService == null){
            return baseVo.error("user check failed");
        }
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        Map<String,Object> params = new HashMap<>(1);
        params.put("credenceUnique",userInfo.getOpenId());
        BaseVo tokenData = authUserService.createAuthenticationToken(params);
        if( tokenData != null && tokenData.getError() == 0){
            Map<String,Object> returnMap = new HashMap<>(2);
            returnMap.put("tokenData",tokenData.getData());
            returnMap.put("userData",userInfo);
            return baseVo.ok(returnMap,"解密用户信息成功");
        }
        return baseVo.ok(null,"解密用户信息失败");
    }

    @Override
    public BaseVo phone(Integer userType, String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        WxMaService wxService = checkUserInfo(userType, sessionKey, signature, rawData);
        if(wxService == null){
            return baseVo.error("user check failed");
        }
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        return baseVo.ok(phoneNoInfo,"获取手机号成功");
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
