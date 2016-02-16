package jp.caron.util;

public class ArgSet<T> {
	private Class<T> type;
	private T value;
	public ArgSet(Class<T> clazz, T value) {
		type = clazz;
		this.value = value;
	}
	public Class<T> getType() {
		return type;
	}
	public T getValue() {
		return value;
	}
}
