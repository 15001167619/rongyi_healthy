package com.etycx.user.service.impl;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.IAuthUserService;
import com.etycx.user.auth.IReqValidator;
import com.etycx.user.auth.dto.AuthResponseImpl;
import com.etycx.user.cache.RedisUtil;
import com.etycx.user.config.JwtTokenConfig;
import com.etycx.user.config.properties.JwtProperties;
import com.kuding.anno.ExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 用户安全 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
@ExceptionListener
public class AuthServiceImpl implements IAuthUserService {

    @Autowired
    private JwtTokenConfig tokenConfig;

    @Autowired
    private RedisUtil redis;

    @Autowired
    private IReqValidator reqValidator;

    @Autowired
    private JwtProperties jwtProperties;

    private final BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo createAuthenticationToken(Map<String,Object> params) {
        String credenceUnique = (String) params.get("credenceUnique");
        if(StringUtils.isEmpty(credenceUnique)){
            return baseVo.ok(false,"获取 token 失败,credenceUnique不存在");
        }
        boolean validate = reqValidator.validate(credenceUnique);
        if (validate) {
            final String randomKey = RandomStringUtils.random(16);
            final String token = tokenConfig.generateToken(credenceUnique, randomKey);
            final String sign = tokenConfig.generateSign(credenceUnique, randomKey);
            redis.set("token_sign_"+credenceUnique,sign,jwtProperties.getExpiration());
            return baseVo.ok(new AuthResponseImpl(token, sign),"获取 token 成功");
        } else {
            return baseVo.ok(false,"获取 token 失败,账号不存在");
        }

    }

    @Override
    public boolean isTokenExpired(String token) {
        return tokenConfig.isTokenExpired(token);
    }

    @Override
    public boolean isSignExpired(String signKey,String signValue) {
        String redisValueByKey = (String) redis.get(signKey);
        if(StringUtils.isBlank(redisValueByKey) || !signValue.equals(redisValueByKey)){
            return true;
        }
        return false;
    }
}
