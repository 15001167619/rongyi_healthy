package com.etycx.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 武海升
 * @date  2019-08-07
 */
@SpringBootApplication
@EnableDubbo
@Slf4j
public class UserRolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRolesApplication.class, args);
        log.info("UserRolesApplication is start up success");
    }

}
