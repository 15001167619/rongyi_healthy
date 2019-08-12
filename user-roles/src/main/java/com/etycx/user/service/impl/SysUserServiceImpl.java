package com.etycx.user.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.SysUser;
import com.etycx.remote.entity.WechatUser;
import com.etycx.remote.service.ISysUserService;
import com.etycx.user.mapper.SysUserMapper;
import com.etycx.user.mapper.WechatUserMapper;
import com.github.binarywang.java.emoji.EmojiConverter;
import com.kuding.anno.ExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
@ExceptionListener
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private WechatUserMapper wechatUserMapper;

    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Override
    public boolean saveWechatUser(WxMaUserInfo userInfo, Integer userType) {
//        BiFunction<WxMaUserInfo,Integer,Boolean> initUserFunction = new SysUserServiceImpl() ::collectUserInfo;
//        return initUserFunction.apply(userInfo,userType);
        return false;
    }

    @Override
    public int initWechatUserPhone(String openId, String phone) {
//        return wechatUserMapper.update(WechatUser
//                .builder()
//                .phone(phone)
//                .build(),new QueryWrapper<WechatUser>().eq("open_id", openId));

        return 0;
    }

    private boolean collectUserInfo(WxMaUserInfo userInfo,Integer userIdentity) {
        try {
            Integer count = wechatUserMapper.selectCount(new QueryWrapper<WechatUser>().eq("open_id", userInfo.getOpenId()));
            if(count>0){
                return true;
            }
            String nickName = userInfo.getNickName();
            if(StringUtils.isNotBlank(nickName)){
                nickName = emojiConverter.toAlias(nickName);
            }
            wechatUserMapper.insert(WechatUser
                    .builder()
                    .openId(userInfo.getOpenId())
                    .gender(Integer.valueOf(userInfo.getGender()))
                    .language(userInfo.getLanguage())
                    .city(userInfo.getCity())
                    .province(userInfo.getProvince())
                    .country(userInfo.getCountry())
                    .avatarUrl(userInfo.getAvatarUrl())
                    .nickName(nickName)
                    .userIdentity(userIdentity)
                    .build());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
