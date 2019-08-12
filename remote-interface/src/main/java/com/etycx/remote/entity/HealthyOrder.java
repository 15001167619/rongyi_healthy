package com.etycx.remote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *      体检订单
 * </p>
 *
 * @author 武海升
 * @date  2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthyOrder extends Model<HealthyOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 体检类型 0 个人体检 1 企业体检
     */
    private Integer type;

    /**
     * 状态 0 待确认 1 待付款 2 已付款
     */
    private Integer status;

    /**
     * 订单总金额
     */
    private Double price;

    /**
     * 微信标识openID
     */
    private String remarks;

    /**
     * 用户所在国家
     */
    private String qrCode;

    /**
     * 0 未使用 1 已使用（失效）
     */
    private Integer qrCodeStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
