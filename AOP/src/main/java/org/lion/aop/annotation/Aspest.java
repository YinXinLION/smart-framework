package org.lion.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by 殷鑫 on 2017/5/26.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspest {
    Class<? extends Annotation> value();
}
