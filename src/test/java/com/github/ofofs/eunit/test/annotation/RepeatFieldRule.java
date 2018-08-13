package com.github.ofofs.eunit.test.annotation;

import com.github.ofofs.eunit.annotation.Constraint;
import com.github.ofofs.eunit.test.constraint.RepeatFieldRuler;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p> 用户自己定义的注解 </p>
 *
 * <pre> Created: 2018/8/13 下午3:47  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Documented
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(value = RepeatFieldRuler.class)
public @interface RepeatFieldRule {

    /**
     * 重复的字段
     * @return
     */
    String value();

    /**
     * 描述
     * @return
     */
    String desc() default "";

}
