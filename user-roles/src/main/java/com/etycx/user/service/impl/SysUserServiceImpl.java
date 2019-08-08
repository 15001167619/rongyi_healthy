package com.etycx.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.SysUser;
import com.etycx.remote.service.ISysUserService;
import com.etycx.user.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
