package com.etycx.user;

import com.etycx.remote.response.BaseVo;
import com.etycx.remote.service.ISysManagerService;
import com.etycx.remote.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InterfaceTest {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysManagerService sysManagerService;

    /**
     * 菜单测试
     */
    @Test
    public void getMenuList() {
        menuService.getMenuList(new HashMap<>());
    }

    /**
     * 跨库测试
     */
    @Test
    public void testList() {
        BaseVo baseVo = sysManagerService.testList(new HashMap<>());
        System.out.println(baseVo);
    }

}
