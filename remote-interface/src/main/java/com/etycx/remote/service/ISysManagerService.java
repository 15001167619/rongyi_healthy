package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

import java.util.Map;

/**
 * <p>
 *      管理员查询 服务类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-13
 */
public interface ISysManagerService{

    /**
     * <p>
     *     测试跨库查询
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params healthExamination 测试跨库查询体检订单
     * @return boolean
     */
    BaseVo testList(Map<String, Object> params);
    /**
     * <p>
     *      获取全部订单
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params healthExamination 指定某家医院的体检订单
     * @return boolean
     */
    BaseVo orderList(Map<String, Object> params);


}
