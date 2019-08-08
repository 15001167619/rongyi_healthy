package com.etycx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etycx.remote.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * <p>
     *  获取菜单列表
     * </p>
     *
     * @author 武海升
     * @date  2019-08-07
     * @param roleIds
     *      roleIds 角色id集合 以逗号相隔  1,3  可不传
     * @return BaseVo
     */
    List<SysMenu> findMenuList(@Param(value = "roleIds") String roleIds);

}
