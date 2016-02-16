package jp.caron.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InjectUtil {

	public static void setProperty(Object target, String name, Object val) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (target != null) {
			Field f = target.getClass().getDeclaredField(name);
			f.setAccessible(true);
			f.set(target, val);
		}
	}

	public static void setProperty(Object target, Field field, Object val) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (target != null) {
			field.setAccessible(true);
			field.set(target, val);
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

	public static Object getProperty(Object target, Field field) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (target != null) {
			field.setAccessible(true);
			return field.get(target);
		}
		return null;
	}

	public static Object invoke(Object o, String name, ArgSet<?>...args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] parameterTypes = null;
		Object[] values = null;

		if (args.length > 0) {
			parameterTypes = new Class<?>[args.length];
			values = new Object[args.length];
			int x = 0;
			for (ArgSet<?> arg : args) {
				parameterTypes[x] = arg.getType();
				values[x] = arg.getValue();
				x++;
			}
		}
		Method m = o.getClass().getDeclaredMethod(name, parameterTypes);
		m.setAccessible(true);
		return m.invoke(o, values);
	}

	public static Object invoke(Object o, Method m, ArgSet<?>...args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return InjectUtil.invoke(o, m.getName(), args);
	}



	/**
	 * プロパティ設定。取得、メソッド実行、結果取得　定義
	 * @return
	 */
	public static PropertyListener newPropertyListener() {
		return new PropertyListener() {

			@Override
			public Object getProperty(Object o, String name) {
				try {
					return InjectUtil.getProperty(o, name);
				} catch (Throwable t) {
					throw new RuntimeException("get property error 1", t);
				}
			}

			@Override
			public Object getProperty(Object o, Field field) {
				try {
					return InjectUtil.getProperty(o, field);
				} catch (Throwable t) {
					throw new RuntimeException("get property error 2", t);
				}
			}

			@Override
			public void setProperty(Object o, String name, Object val) {
				try {
					InjectUtil.setProperty(o, name, val);
				} catch (Throwable t) {
					throw new RuntimeException("set property error 1", t);
				}
			}

			@Override
			public void setProperty(Object o, Field field, Object val) {
				try {
					InjectUtil.setProperty(o, field, val);
				} catch (Throwable t) {
					throw new RuntimeException("set property error 2", t);
				}
			}

			@Override
			public Object invoke(Object o, String name, ArgSet<?>... args) {
				try {
					return InjectUtil.invoke(o, name, args);
				} catch (Throwable t) {
					throw new RuntimeException("invoke method error 1", t);
				}
			}

			@Override
			public Object invoke(Object o, Method method, ArgSet<?>... args) {
				try {
					return InjectUtil.invoke(o, method, args);
				} catch (Throwable t) {
					throw new RuntimeException("invoke method error 2", t);
				}
			}};
	}
}
