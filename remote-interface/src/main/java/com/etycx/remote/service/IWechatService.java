package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      微信管理
 * </p>
 *
 * @author 武海升
 * @date  2019-08-08
 */
public interface IWechatService{

    /**
     * <p>
     * 微信授权解析用户信息
     * </p>
     *
     * @author 武海升
     * @date  2019-08-08
     * @param userType
     * @param sessionKey
     * @param signature
     * @param rawData
     * @param encryptedData
     * @param iv
     * @return BaseVo
     */
    BaseVo userInfo(Integer userType,String sessionKey,String signature, String rawData, String encryptedData, String iv);

    /**
     * <p>
     * 微信授权获取用户手机号
     * </p>
     *
     * @author 武海升
     * @date  2019-08-08
     * @param userType
     * @param sessionKey
     * @param signature
     * @param rawData
     * @param encryptedData
     * @param iv
     * @param openId
     * @return BaseVo
     */
    BaseVo phone(Integer userType,String sessionKey, String signature,String rawData, String encryptedData, String iv, String openId);

    /**
     * <p>
     *   获取支付的请求参数
     *   微信统一订单下单
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param orderId
     * @return BaseVo
     */
    BaseVo prepay(Integer orderId);

    /**
     * <p>
     *   微信支付回调
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param request
     * @param response
     */
    void notify(HttpServletRequest request, HttpServletResponse response);
}
