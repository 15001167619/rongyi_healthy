package com.etycx.remote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单名称
     */
    private String label;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 请求地址
     */
    private String path;


    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String status;

    /**
     * 图标
     */
    private String  icon;

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
