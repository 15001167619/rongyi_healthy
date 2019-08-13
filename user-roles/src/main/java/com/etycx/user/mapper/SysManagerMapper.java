package com.etycx.user.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *       管理员查询服务
 * </p>
 *
 * @author 武海升
 * @date  2019-08-13
 */
public interface SysManagerMapper{

    /**
     * <p>
     *       跨库测试查询
     * </p>
     *
     * @author 武海升
     * @date  2019-08-13
     * @param params
     * @return BaseVo
     */
    List<Map<String,Object>> testList(@Param(value = "params") Map<String, Object> params);

}
