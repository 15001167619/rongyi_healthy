package com.etycx.remote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表 平台全部用户数据
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
public class WechatUser extends Model<WechatUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户身份：1体检端 2 管理端
     */
    private Integer userIdentity;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像图片的 URL
     */
    private String avatarUrl;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 性别0未知1男2女
     */
    private Integer gender;

    /**
     * 微信标识openID
     */
    private String openId;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 语言 en英文zh_CN简体中文zh_TW繁体中文
     */
    private String language;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
