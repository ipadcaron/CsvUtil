package jp.caron.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface PropertyListener {
	/**
	 * get property
	 * @param o
	 * @param name
	 * @return
	 */
	Object prop(Object o, String name);

	/**
	 * get property
	 * @param o
	 * @param field
	 * @return
	 */
	Object prop(Object o, Field field);

	/**
	 * set property
	 * @param o
	 * @param name
	 * @param val
	 */
	void prop(Object o, String name, Object val);

	/**
	 * set property
	 * @param o
	 * @param field
	 * @param val
	 */
	void prop(Object o, Field field, Object val);

	/**
	 * invoke method
	 * @param o
	 * @param name
	 * @param args
	 * @return
	 */
	Object call(Object o, String name, ArgSet<?>...args);

	/**
	 * invoke method
	 * @param o
	 * @param method
	 * @param args
	 * @return
	 */
	Object call(Object o, Method method, ArgSet<?>...args);

	/**
	 * new instance
	 * @param clazz int 3次元配列 なら、int[][][].class を指定する
	 * @param dim 配列の大きさ、... 並べて次元数
	 * @return
	 */
	<T> T newArrayInstance(Class<? super T> clazz, int...dim);

	// primitive <=> primitive Class typed convert each other.
	// list <=> array
	//void tset(Object o, String name, Object val);
	// TODO:
}
