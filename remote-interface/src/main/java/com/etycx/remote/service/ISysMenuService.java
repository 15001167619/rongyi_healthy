package com.etycx.remote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etycx.remote.entity.SysMenu;
import com.etycx.remote.response.BaseVo;

import java.util.Map;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * <p>
     *  获取菜单列表
     * </p>
     *
     * @author 武海升
     * @date  2019-08-07
     * @param params
     *      roleIds 角色id集合 以逗号相隔  1,3  可不传
     * @return BaseVo
     */
    BaseVo getMenuList(Map<String, Object> params);

}
