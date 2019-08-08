package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

import java.util.Map;

/**
 * <p>
 * 安全 服务类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public interface IAuthUserService{

    /**
     * <p>
     *  生成安全令牌token、sign
     * </p>
     *
     * @author 武海升
     * @date  2019-08-08
     * @param params
     * @return boolean
     */
    BaseVo createAuthenticationToken(Map<String,Object> params);

    /**
     * <p>
     *  token 是否过期
     * </p>
     *
     * @author 武海升
     * @date  2019-08-07
     * @param token
     * @return boolean
     */
    boolean isTokenExpired(String token);


    /**
     * <p>
     *  sign 是否过期
     * </p>
     *
     * @author 武海升
     * @date  2019-08-07
     * @param signValue
     * @param signKey
     * @return boolean
     */
    boolean isSignExpired(String signKey,String signValue);

}
