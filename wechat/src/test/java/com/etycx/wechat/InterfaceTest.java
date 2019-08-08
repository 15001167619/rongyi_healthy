package com.etycx.wechat;

import com.etycx.remote.service.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InterfaceTest {

    @Autowired
    private IWechatService wechatService;

    /**
     * 菜单测试
     */
    @Test
    public void getMenuList() {
        wechatService.userInfo(0,"","","","","");
    }

}
