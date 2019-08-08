package com.etycx.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etycx.remote.entity.SysRoleMenu;
import com.etycx.remote.service.ISysRoleMenuService;
import com.etycx.user.mapper.SysRoleMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {


}
