package jp.caron.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<? super T> clazz,int...dim) throws InstantiationException, IllegalAccessException {
		if (dim.length > 0) {
			Class<?> clz = clazz.getComponentType();
			while (clz != null) {
				Class<?> tmp = clz.getComponentType();
				if (tmp == null) {
					break;
				}
				clz = tmp;
			}
			if (clz != null) {
				return (T) Array.newInstance(clz, dim);
			} else {
				return (T) Array.newInstance(clazz, dim);
			}
		} else {
			return (T) clazz.newInstance();
		}
	}

	private static Map<Object, Object> map;

	public static void setWithTypeConvert(Object o, String name, Object val) {
		if (val != null) {
			// TODO:
		}
	}

	@SuppressWarnings("unchecked")
	public static <R> R copyBean(R bean) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		R r = (R) bean.getClass().newInstance();

		for (Field f : bean.getClass().getDeclaredFields()) {
			Object val = InjectUtil.getProperty(bean, f);
			InjectUtil.setProperty(r, f, val);
		}

		return r;
	}
	

	/**
	 * プロパティ設定。取得、メソッド実行、結果取得　定義
	 * @return
	 */
	public static PropertyListener newPropertyListener() {
		return new PropertyListener() {

			@Override
			public Object prop(Object o, String name) {
				try {
					return InjectUtil.getProperty(o, name);
				} catch (Throwable t) {
					throw new RuntimeException("get property error 1", t);
				}
			}

			@Override
			public Object prop(Object o, Field field) {
				try {
					return InjectUtil.getProperty(o, field);
				} catch (Throwable t) {
					throw new RuntimeException("get property error 2", t);
				}
			}

			@Override
			public void prop(Object o, String name, Object val) {
				try {
					InjectUtil.setProperty(o, name, val);
				} catch (Throwable t) {
					throw new RuntimeException("set property error 1", t);
				}
			}

			@Override
			public void prop(Object o, Field field, Object val) {
				try {
					InjectUtil.setProperty(o, field, val);
				} catch (Throwable t) {
					throw new RuntimeException("set property error 2", t);
				}
			}

			@Override
			public Object call(Object o, String name, ArgSet<?>... args) {
				try {
					return InjectUtil.invoke(o, name, args);
				} catch (Throwable t) {
					throw new RuntimeException("invoke method error 1", t);
				}
			}

			@Override
			public Object call(Object o, Method method, ArgSet<?>... args) {
				try {
					return InjectUtil.invoke(o, method, args);
				} catch (Throwable t) {
					throw new RuntimeException("invoke method error 2", t);
				}
			}

			@Override
			public <T> T news(Class<? super T> clazz, int...dim) {
				try {
					return InjectUtil.newInstance(clazz , dim);
				} catch (Throwable t) {
					throw new RuntimeException("new instance error ", t);
				}
			}
		};
	}
}
