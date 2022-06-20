package site.metacoding.reflect.config.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 타겟범위 : 메서드 위에만 붙은 어노테이션
@Retention(RetentionPolicy.RUNTIME) // JVM 런타임시에 읽어라
public @interface RequestMapping {
 String value(); // 어노테이션의 value 메서드가 디폴트 키 값 (키 생략가능)
}
