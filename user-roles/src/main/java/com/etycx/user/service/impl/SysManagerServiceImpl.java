package com.etycx.user.service.impl;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.ISysManagerService;
import com.etycx.user.mapper.SysManagerMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *       管理员查询服务实现类
 * </p>
 *
 * @author 武海升
 * @date  2019-08-13
 */
@Service(version = "${dubbo.version}")
@Component
@Slf4j
//@ExceptionListener
public class SysManagerServiceImpl implements ISysManagerService {

    @Autowired
    private SysManagerMapper sysManagerMapper;

    private BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo testList(Map<String, Object> params) {
        List<Map<String, Object>> list = sysManagerMapper.testList(params);
        return baseVo.ok(list,"数据跨库查询");
    }

    @Override
    public BaseVo orderList(Map<String, Object> params) {
        return null;
    }
}
