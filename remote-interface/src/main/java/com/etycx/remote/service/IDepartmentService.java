package com.etycx.remote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etycx.remote.entity.Department;
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
public interface IDepartmentService extends IService<Department> {

    /**
     * <p>
     *      获取科室列表
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params 不需要需要分页
     * @return BaseVo
     */
    BaseVo getDepartmentList(Map<String, Object> params);

    /**
     * <p>
     *      添加科室
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo addDepartment(Map<String, Object> params);

    /**
     * <p>
     *      修改科室
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo updateDepartment(Map<String, Object> params);

    /**
     * <p>
     *      删除科室
     * </p>
     *
     * @author 武海升
     * @date  2019-08-12
     * @param params
     * @return BaseVo
     */
    BaseVo delDepartment(Map<String, Object> params);

}
