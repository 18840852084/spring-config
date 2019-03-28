package com.zgl.spring.environment.aop;

import java.lang.annotation.*;

/**
 * @author zgl
 * @date 2019/1/22 上午10:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
}
