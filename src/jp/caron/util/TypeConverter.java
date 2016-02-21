package jp.caron.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TypeConverter {

	/**
	 * テスト
	 * @param args
	 */
	public static void main(String[] args) {

		test(Double.class, 156D, Double.class);
		test(Double.class, 156F, Float.class);
		test(Double.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Double.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Double.class, 156L, Long.class);
		test(Double.class, 156, Integer.class);
		test(Double.class, (short)156, Short.class);
		test(Double.class, (byte)156, Byte.class);
		test(Double.class, (char)'E', Character.class);
		test(Double.class, "15645", String.class);
		System.out.println("-----------------");
		test(Float.class, 156D, Double.class);
		test(Float.class, 156F, Float.class);
		test(Float.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Float.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Float.class, 156L, Long.class);
		test(Float.class, 156, Integer.class);
		test(Float.class, (short)156, Short.class);
		test(Float.class, (byte)156, Byte.class);
		test(Float.class, (char)'E', Character.class);
		test(Float.class, "15645", String.class);
		System.out.println("-----------------");
		test(Long.class, 156D, Double.class);
		test(Long.class, 156F, Float.class);
		test(Long.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Long.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Long.class, 156L, Long.class);
		test(Long.class, 156, Integer.class);
		test(Long.class, (short)156, Short.class);
		test(Long.class, (byte)156, Byte.class);
		test(Long.class, (char)'E', Character.class);
		test(Long.class, "15645", String.class);
		System.out.println("-----------------");
		test(Integer.class, 156D, Double.class);
		test(Integer.class, 156F, Float.class);
		test(Integer.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Integer.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Integer.class, 156L, Long.class);
		test(Integer.class, 156, Integer.class);
		test(Integer.class, (short)156, Short.class);
		test(Integer.class, (byte)156, Byte.class);
		test(Integer.class, (char)'E', Character.class);
		test(Integer.class, "15645", String.class);
		System.out.println("-----------------");
		test(Short.class, 156D, Double.class);
		test(Short.class, 156F, Float.class);
		test(Short.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Short.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Short.class, 156L, Long.class);
		test(Short.class, 156, Integer.class);
		test(Short.class, (short)156, Short.class);
		test(Short.class, (byte)156, Byte.class);
		test(Short.class, (char)'E', Character.class);
		test(Short.class, "15645", String.class);
		System.out.println("-----------------");
		test(Character.class, 156D, Double.class);
		test(Character.class, 156F, Float.class);
		test(Character.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Character.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Character.class, 156L, Long.class);
		test(Character.class, 156, Integer.class);
		test(Character.class, (char)156, Short.class);
		test(Character.class, (byte)156, Byte.class);
		test(Character.class, (char)'E', Character.class);
		test(Character.class, "15645", String.class);
		System.out.println("-----------------");
		test(Byte.class, 156D, Double.class);
		test(Byte.class, 156F, Float.class);
		test(Byte.class, BigInteger.valueOf(156L), BigInteger.class);
		test(Byte.class, BigDecimal.valueOf(156L), BigDecimal.class);
		test(Byte.class, 156L, Long.class);
		test(Byte.class, 156, Integer.class);
		test(Byte.class, (char)156, Short.class);
		test(Byte.class, (byte)156, Byte.class);
		test(Byte.class, (char)'E', Character.class);
		test(Byte.class, "15645", String.class);

	}

	static void test(Class<?> retType, Object in, Class<?> setType) {
		Object ret = TypeConverter.to(retType, in);
		Object out = TypeConverter.to(setType, ret);

		String rs = retType.getName().substring(retType.getName().lastIndexOf('.') + 1);
		String os = setType.getName().substring(setType.getName().lastIndexOf('.') + 1);

		String hex = "";
		if (out instanceof Integer || out instanceof Short || out instanceof Byte) {
			hex = String.format("%x", out);
		}
		System.out.println(rs + " <=> " + os + "  >>>in(" + in + ")  " + ret + " :: " + out + " >>> " + hex);
	}

	/**
	 * o を rType に変換する。o != null は上位で判定する。
	 * assert 機能を有効にするには、コンパイルオプション -ea が必要らしい。
	 * @param rType
	 * @param o
	 * @return
	 */
	public static Object to(Class<?> rType, Object o) {
		assert o != null : "from operand requires not null.";

		if (rType.equals(String.class)) {
			if (o != null) {
				return o.toString();
			}
			return null;
		}

		String si = rType.getName() + o.getClass().getName();
		Function<Object, ?> fn = map.get(si);
		if (fn != null) {
			return fn.apply(o);
		}
		return o;

	}

	private static Map<String, Function<Object, ?>> map;
	static {
		map = new HashMap<>(120);

		// Double to others
		map.put(Double.class.getName() + BigDecimal.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((BigDecimal)t).doubleValue();
			}});
		map.put(Double.class.getName() + BigInteger.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((BigInteger)t).doubleValue();
			}});
		map.put(Double.class.getName() + Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return (Double)t;
			}});
		map.put(Double.class.getName() + Float.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((Float)t).doubleValue();
			}});
		map.put(Double.class.getName() + Long.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((Long)t).doubleValue();
			}});
		map.put(Double.class.getName() + Integer.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((Integer)t).doubleValue();
			}});
		map.put(Double.class.getName() + Short.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((Short)t).doubleValue();
			}});
		map.put(Double.class.getName() + Character.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return Integer.valueOf(((int)((char)t)) & 0x0FFFF).doubleValue();
			}});
		map.put(Double.class.getName() + Byte.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return ((Byte)t).doubleValue();
			}});
		map.put(Double.class.getName() + String.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Object t) {
				return Double.valueOf(t.toString());
			}});

		// Float to others
		map.put(Float.class.getName() + BigDecimal.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((BigDecimal)t).floatValue();
			}});
		map.put(Float.class.getName() + BigInteger.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((BigInteger)t).floatValue();
			}});
		map.put(Float.class.getName() + Double.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((Double)t).floatValue();
			}});
		map.put(Float.class.getName() + Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return (Float)t;
			}});
		map.put(Float.class.getName() + Long.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((Long)t).floatValue();
			}});
		map.put(Float.class.getName() + Integer.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((Integer)t).floatValue();
			}});
		map.put(Float.class.getName() + Short.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((Short)t).floatValue();
			}});
		map.put(Float.class.getName() + Character.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return Integer.valueOf((int)((char)t) & 0x0FFFF).floatValue();
			}});
		map.put(Float.class.getName() + Byte.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return ((Byte)t).floatValue();
			}});
		map.put(Float.class.getName() + String.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Object t) {
				return Float.valueOf(t.toString());
			}});

		// Long to others
		map.put(Long.class.getName() + BigDecimal.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((BigDecimal)t).longValue();
			}});
		map.put(Long.class.getName() + BigInteger.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((BigInteger)t).longValue();
			}});
		map.put(Long.class.getName() + Double.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((Double)t).longValue();
			}});
		map.put(Long.class.getName() + Float.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((Float)t).longValue();
			}});
		map.put(Long.class.getName() + Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return (Long)t;
			}});
		map.put(Long.class.getName() + Integer.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((Integer)t).longValue();
			}});
		map.put(Long.class.getName() + Short.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((Short)t).longValue();
			}});
		map.put(Long.class.getName() + Character.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return Integer.valueOf((int)((char)t) & 0x0FFFF).longValue();
			}});
		map.put(Long.class.getName() + Byte.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return ((Byte)t).longValue();
			}});
		map.put(Long.class.getName() + String.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Object t) {
				return Long.valueOf(t.toString());
			}});

		// Integer to others
		map.put(Integer.class.getName() + BigDecimal.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((BigDecimal)t).intValue();
			}});
		map.put(Integer.class.getName() + BigInteger.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((BigInteger)t).intValue();
			}});
		map.put(Integer.class.getName() + Double.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((Double)t).intValue();
			}});
		map.put(Integer.class.getName() + Float.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((Float)t).intValue();
			}});
		map.put(Integer.class.getName() + Long.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((Long)t).intValue();
			}});
		map.put(Integer.class.getName() + Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return (Integer)t;
			}});
		map.put(Integer.class.getName() + Short.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((Short)t).intValue();
			}});
		map.put(Integer.class.getName() + Character.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return Integer.valueOf((int)((char)t) & 0x0FFFF);
			}});
		map.put(Integer.class.getName() + Byte.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return ((Byte)t).intValue();
			}});
		map.put(Integer.class.getName() + String.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				return Integer.valueOf(t.toString());
			}});

		// Short to others
		map.put(Short.class.getName() + BigDecimal.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((BigDecimal)t).shortValue();
			}});
		map.put(Short.class.getName() + BigInteger.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((BigInteger)t).shortValue();
			}});
		map.put(Short.class.getName() + Double.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((Double)t).shortValue();
			}});
		map.put(Short.class.getName() + Float.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((Float)t).shortValue();
			}});
		map.put(Short.class.getName() + Long.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((Long)t).shortValue();
			}});
		map.put(Short.class.getName() + Integer.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((Integer)t).shortValue();
			}});
		map.put(Short.class.getName() + Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return (Short)t;
			}});
		map.put(Short.class.getName() + Character.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return Integer.valueOf((int)((char)t) & 0x0FFFF).shortValue();
			}});
		map.put(Short.class.getName() + Byte.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return ((Byte)t).shortValue();
			}});
		map.put(Short.class.getName() + String.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Object t) {
				return Short.valueOf(t.toString());
			}});

		// Character to others
		map.put(Character.class.getName() + BigDecimal.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((BigDecimal)t).intValue());
			}});
		map.put(Character.class.getName() + BigInteger.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((BigInteger)t).intValue());
			}});
		map.put(Character.class.getName() + Double.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Double)t).intValue());
			}});
		map.put(Character.class.getName() + Float.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Float)t).intValue());
			}});
		map.put(Character.class.getName() + Long.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Long)t).intValue());
			}});
		map.put(Character.class.getName() + Integer.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Integer)t).intValue());
			}});
		map.put(Character.class.getName() + Short.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Short)t).intValue());
			}});
		map.put(Character.class.getName() + Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return (Character)t;
			}});
		map.put(Character.class.getName() + Byte.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				return Character.valueOf((char)((Byte)t).intValue());
			}});
		map.put(Character.class.getName() + String.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Object t) {
				// かいざん
				// TODO:
				return ((String)t).charAt(0);
			}});

		// Byte to others
		map.put(Byte.class.getName() + BigDecimal.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((BigDecimal)t).byteValue();
			}});
		map.put(Byte.class.getName() + BigInteger.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((BigInteger)t).byteValue();
			}});
		map.put(Byte.class.getName() + Double.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((Double)t).byteValue();
			}});
		map.put(Byte.class.getName() + Float.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((Float)t).byteValue();
			}});
		map.put(Byte.class.getName() + Long.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((Long)t).byteValue();
			}});
		map.put(Byte.class.getName() + Integer.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((Integer)t).byteValue();
			}});
		map.put(Byte.class.getName() + Short.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return ((Short)t).byteValue();
			}});
		map.put(Byte.class.getName() + Character.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return Integer.valueOf((int)((char)t) & 0x0FFFF).byteValue();
			}});
		map.put(Byte.class.getName() + Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				return (Byte)t;
			}});
		map.put(Byte.class.getName() + String.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Object t) {
				//TODO:
				return Integer.valueOf(t.toString()).byteValue();
			}});
/*
 * TODO: 課題、String...
		// String to others
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(BigDecimal t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(BigInteger t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Double t) {
				return t;
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Float t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Long t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Integer t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Short t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF).doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(Byte t) {
				return t.doubleValue();
			}});
		map.put(Double.class.getName(), new Function<Object, Double>() {
			@Override
			public Double apply(String t) {
				return Double.valueOf(t);
			}});
*/

		// BigDecimal to others
		map.put(BigDecimal.class.getName() + BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return (BigDecimal)t;
			}});
		map.put(BigDecimal.class.getName() + BigInteger.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				//Why Exact , Exact 使うと例外が出るので、キャストの目的としては
				// 普通の longValue でOK
				return BigDecimal.valueOf(((BigInteger)t).longValue());
			}});
		map.put(BigDecimal.class.getName() + Double.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Double)t).doubleValue());
			}});
		map.put(BigDecimal.class.getName() + Float.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Float)t).floatValue());
			}});
		map.put(BigDecimal.class.getName() + Long.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Long)t).longValue());
			}});
		map.put(BigDecimal.class.getName() + Integer.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Integer)t).longValue());
			}});
		map.put(BigDecimal.class.getName() + Short.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Short)t).longValue());
			}});
		map.put(BigDecimal.class.getName() + Character.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(Long.valueOf((long)((char)t) & 0x0FFFF).longValue());
			}});
		map.put(BigDecimal.class.getName() + Byte.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return BigDecimal.valueOf(((Byte)t).byteValue());
			}});
		map.put(BigDecimal.class.getName() + String.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Object t) {
				return new BigDecimal(t.toString());
			}});

		// BigInteger to others
		map.put(BigInteger.class.getName() + BigDecimal.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return ((BigDecimal)t).toBigInteger();
			}});
		map.put(BigInteger.class.getName() + BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return (BigInteger)t;
			}});
		map.put(BigInteger.class.getName() + Double.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Double)t).longValue());
			}});
		map.put(BigInteger.class.getName() + Float.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Float)t).longValue());
			}});
		map.put(BigInteger.class.getName() + Long.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Long)t).longValue());
			}});
		map.put(BigInteger.class.getName() + Integer.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Integer)t).longValue());
			}});
		map.put(BigInteger.class.getName() + Short.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Short)t).longValue());
			}});
		map.put(BigInteger.class.getName() + Character.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(Long.valueOf((long)((char)t) & 0x0FFFF).longValue());
			}});
		map.put(BigInteger.class.getName() + Byte.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return BigInteger.valueOf(((Byte)t).longValue());
			}});
		map.put(BigInteger.class.getName() + String.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Object t) {
				return new BigInteger(t.toString());
			}});

	};
}
