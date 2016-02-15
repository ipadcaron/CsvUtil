package jp.caron.util;

import java.lang.reflect.Field;

public class InjectUtil {

	public static void setProperty(Object target, String name, Object val) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (target != null) {
			Field f = target.getClass().getDeclaredField(name);
			f.setAccessible(true);
			f.set(target, val);
		}
	}

	public static Object getProperty(Object target, String name) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (target != null) {
			Field f = target.getClass().getDeclaredField(name);
			f.setAccessible(true);
			return f.get(target);
		}
		return null;
	}
}
