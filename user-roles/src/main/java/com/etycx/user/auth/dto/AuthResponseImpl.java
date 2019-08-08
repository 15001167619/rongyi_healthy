package com.etycx.user.auth.dto;

import java.io.Serializable;

/**
 * <p>
 * 用户安全 返回值
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public class AuthResponseImpl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jwt token
     */
    private final String token;

    /**
     * 签名
     */
    private final String sign;

    public AuthResponseImpl(String token, String sign) {
        this.token = token;
        this.sign = sign;
    }

    public String getToken() {
        return this.token;
    }

    public String getSign() {
        return sign;
    }

}
