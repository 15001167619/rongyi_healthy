package health.examination.two;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 武海升
 * @date  2019-08-12
 */
@SpringBootApplication
@EnableDubbo
@Slf4j
public class HealthExaminationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthExaminationApplication.class, args);
        log.info("HealthExaminationApplication is start up success");
    }

}
