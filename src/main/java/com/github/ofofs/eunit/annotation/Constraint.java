package com.github.ofofs.eunit.annotation;

import com.github.ofofs.eunit.core.ConstraintAnnotationRuler;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p> 自定义约束注解 </p>
 *
 * <pre> Created: 2018/8/13 上午10:29  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.8
 */
@Documented
@Target({ TYPE })
@Retention(RUNTIME)
public @interface Constraint {

    /**
     * 指定约束类的规则
     * @return 约束类
     */
    Class<? extends ConstraintAnnotationRuler<? extends Annotation, ?>> value();

}
