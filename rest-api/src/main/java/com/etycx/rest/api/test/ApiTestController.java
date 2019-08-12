package com.etycx.rest.api.test;

import com.etycx.remote.service.IDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 武海升
 * @desc 测试Api
 * @date  2019-08-12
 */
@RestController
@RequestMapping("api")
@Slf4j
public class ApiTestController {

    /**
     * 第一家医院的 科室管理
     */
    @Reference(version = "1.0.0",check = false,group = "health-examination-one")
    private IDepartmentService departmentServiceOne;

    /**
     * 第二家医院的 科室管理
     */
    @Reference(version = "1.0.0",check = false,group = "health-examination-two")
    private IDepartmentService departmentServiceTwo;

    /**
     * @description  测试
     */
    @GetMapping("test")
    public Object test(){
        departmentServiceOne.getDepartmentList(null);
        log.info("~~~~~~~~~~~~~~");
        departmentServiceTwo.getDepartmentList(null);
        return true;
    }

}
