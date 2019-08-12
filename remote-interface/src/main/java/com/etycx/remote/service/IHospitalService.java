package com.etycx.remote.service;

import com.etycx.remote.response.BaseVo;

import java.util.Map;

/**
 * <p>
 *      医院管理 服务类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-07
 */
public interface IHospitalService {

    /**
     * <p>
     *      获取医院列表
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params 需要分页
     * @return BaseVo
     */
    BaseVo getHospitalList(Map<String, Object> params);

    /**
     * <p>
     *      添加医院
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo addHospital(Map<String, Object> params);

    /**
     * <p>
     *      修改医院
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo updateHospital(Map<String, Object> params);

    /**
     * <p>
     *      删除医院
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo delHospital(Map<String, Object> params);

}
