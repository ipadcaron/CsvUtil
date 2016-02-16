package jp.caron.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface PropertyListener {
	Object getProperty(Object o, String name);
	Object getProperty(Object o, Field field);
	void setProperty(Object o, String name, Object val);
	void setProperty(Object o, Field field, Object val);
	Object invoke(Object o, String name, ArgSet<?>...args);
	Object invoke(Object o, Method method, ArgSet<?>...args);

	// primitive <=> primitive Class typed convert each other.
	// list <=> array
	//void setWithTypeConvert(Object o, String name, Object val);
	// TODO:
}
