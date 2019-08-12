package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

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



}
