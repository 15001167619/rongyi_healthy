package com.etycx.rest.common.annotation;

import java.lang.annotation.*;

/**
 * @author 武海升
 * @description 参数拦截
 */
@Inherited
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    long validTime() default 1000L;

    String appKey();

    String interfaceName();

}
