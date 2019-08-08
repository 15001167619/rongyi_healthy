package com.etycx.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.SysUserRole;
import com.etycx.remote.service.ISysUserRoleService;
import com.etycx.user.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
