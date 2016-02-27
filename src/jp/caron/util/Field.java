package jp.caron.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {
	String columnName();
	boolean startWith() default false;
	boolean endsWith() default false;
	boolean contains() default false;
}
