/*
 * Copyright (c)  2018. ofofs Inc.
 * eunit All rights reserved.
 */

package com.ofofs.eunit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 生成规则 </p>
 *
 * Q:
 * 1. 此注解是否需要可以指定具体的类？
 * 2. 如何保证实现类和默认的规则不冲突？
 * 3. 多套规则：暂时考虑用 tag/group 做简单分组
 * 4. 分组/标签 怎么做最简单方便实用？
 * 5. 关联性：其他的字段在制定规则时，可以获取其他字段的属性值和其他一切信息(控制修改)
 * 5.1 如果不同字段之间有关联？
 * 5.2 如果不同实体之间有关联？
 * 5.3 不同实体的依赖，需要模拟 spring ioc?
 * 6. 顺序：bean 的顺序
 * <pre> Created: 2018/7/21 上午8:28  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
@Documented
public @interface Rule {

    // 指定生成的 class

    // 指定生成的 其他规则...

    /**
     * 规则说明
     * @return 规则说明
     */
    String desc() default "";

    /**
     * 标签
     * @return 标签
     */
    String[] tags() default {};

    /**
     * 分组
     * Q： 需要使用 class 吗？
     * @return 分组
     */
    String group() default "";


}
