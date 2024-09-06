package com.hmdp.dataSourceConfig;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface tfDataSource {
    String name() default "";
}
