package com.etycx.user.auth.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etycx.remote.entity.WechatUser;
import com.etycx.user.auth.IReqValidator;
import com.etycx.user.mapper.WechatUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户安全 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service
public class ReqValidatorImpl implements IReqValidator {

    @Value("${specialValidator.enable}")
    private boolean enableValidator;

    @Value("${specialValidator.account}")
    private String account;

    @Resource
    private WechatUserMapper wechatUserMapper;

    @Override
    public boolean validate(String credenceUnique) {
        if(StringUtils.isBlank(credenceUnique)){
            return false;
        }
        //是否开启特殊验证
        if (enableValidator && (account.equals(credenceUnique))) {
            return true;
        } else {
            WechatUser userInfo = wechatUserMapper.selectOne(new QueryWrapper<WechatUser>().eq("open_id", credenceUnique));
            return userInfo == null?false:true;
        }
    }

}
