package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

import java.util.Map;

/**
 * <p>
 *      体检管理
 * </p>
 *
 * @author 武海升
 * @date  2019-08-13
 */
public interface IPhysicalExaminationService {

    /**
     * <p>
     *      个人体检 --- 购买 【体检套餐 +  自选项目】
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     * @return BaseVo 体检项目二维码(待确认)
     */
    BaseVo selectionPhysicalExamination(Map<String, Object> params);


    /**
     * <p>
     *      个人体检 --- 医生确认并生成最终 体检人 购买 【体检套餐 +  自选项目】 体检项目
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     * @return BaseVo 体检项目二维码(待付款) 更新二维码信息
     */
    BaseVo confirmationPhysicalExamination(Map<String, Object> params);



    /**
     * <p>
     *      个人体检 --- 导检台 确认 体检人已购买【体检套餐 +  自选项目】 体检项目并录入体检项目信息
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     * @return BaseVo
     */
    BaseVo confirmationInspection (Map<String, Object> params);

    /**
     * <p>
     *      企业体检  负责人登记，建立企业名片（联系人电话、联系人公司名称、联系人姓名）
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     * @return BaseVo
     */
    BaseVo createBusinessCard(Map<String, Object> params);

    /**
     * <p>
     *      预约体检
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     *      physicalExaminationType 0 个人体检预约 1  企业体检预约
     * @return BaseVo
     */
    BaseVo appointmentPhysicalExamination(Map<String, Object> params);

}
