package com.etycx.user.auth;

/**
 * <p>
 * 用户安全
 * </p>
 *
 * @author 武海升
 * @date  2019-08-08
 */
public interface IReqValidator {
    /**
     * 获取请求参数验证
     */
    boolean validate(String credenceUnique);

}
