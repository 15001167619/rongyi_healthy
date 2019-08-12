package com.etycx.remote.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etycx.remote.entity.SysUser;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * <p>
     * 保存微信会员信息
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param userInfo
     * @param userType
     * @return boolean
     */
    boolean saveWechatUser(WxMaUserInfo userInfo,Integer userType);

    /**
     * <p>
     * 保存微信会员信息
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param openId
     * @param phone
     * @return int
     */
    int initWechatUserPhone(String openId,String phone);

}
