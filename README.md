# EUnit
Easy Unit

## 依赖
```
<dependency>
    <groupId>com.github.ofofs</groupId>
    <artifactId>eunit</artifactId>
    <version>${eunit.version}</version>
</dependency>
```

## 使用
实体类：  
```
package com.github.ofofs.eunit.test.model;

import com.github.ofofs.eunit.annotation.Rule;


/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class User {

    private Long id;

    @Rule(minLength = 5, maxLength = 20)
    private String username;

    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
```

测试类：  
```
package com.github.ofofs.eunit.test;

import com.github.ofofs.eunit.DataFactory;
import com.github.ofofs.eunit.test.model.User;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public class UserSimpleTest {

    public static void main(String[] args) {
        User user = DataFactory.instance(User.class);
        System.out.println(user);
    }

}
```

输出：  
```
User{id=8462870299209684556, username='kolH4D8roMdK2Kpnq', password='Xace7HK'}
```

## 规则
在字段上加上@Rule注解，可以指定字段生成规则，目前有下列规则可用：  

- 字符串型：可以指定最小长度`minLength`、最大长度`maxLength`和正则表达式`regex`。
- 数字类型：可以指定最小值`min`、最大值`max`和精度`precision`。

> 说明: 目前只能生成八大基本数据类型和String、Date、BigDecimal。其他引用类型暂时不会生成，后续会考虑。

## 想法
1. 提供更多细腻的规则。
2. 怎么解实体类和规则注解的耦合？
3. 如何实现多规则？
4. 规则之间如何依赖？
5. 如何优雅的自定义规则？

> 在实现想法的基础上要考虑到用户体验，如果设计的不优雅不好用宁愿不要。





 