package jp.caron.test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

public class ParameterAnnotationTest {

	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Pp {
		String name() default "";
		Class<?> type() default String.class;
	}

	public static void main(String...args) throws Throwable {

		for (Method m : ParameterAnnotationTest.class.getDeclaredMethods()) {
			for (Annotation[] anos : m.getParameterAnnotations()) {
				Stream.of(anos).forEach(a -> System.out.println("parameter anotation: " + a.toString()));
				Stream.of(m.getParameters()).forEach(p -> System.out.println("  parameter:" + p));
			}

		}
	}

	void test(@Pp String ok, @Pp Integer iVal) {

	}
}
