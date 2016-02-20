package jp.caron.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TypeConverter {

	/**
	 * このやり方で。めんどくさいから明日。
	 * BigDecimalとBigInteger は継承可能なクラスなので、この実装は
	 * 完全では無いです。ても、各８個くらいコンストラクタを実装する必要
	 * があるので、、、、基底クラスがこいつらかどうか判定すればいけるかも。。
	 * でも面倒なので課題としておく。
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(TypeConverter.to(Double.class, 123).getClass());
		System.out.println(TypeConverter.to(Double.class, 123L).getClass());
		System.out.println(TypeConverter.to(Double.class, 123F).getClass());
		System.out.println(TypeConverter.to(Double.class, 123D).getClass());
		System.out.println(TypeConverter.to(Double.class, "123").getClass());
		System.out.println(TypeConverter.to(Double.class, 'あ').getClass());

	}

	public static Object to(Class<?> rType, Object o) {
		assert o != null : "from operand requires not null.";
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
/*
		// Float to others
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(BigDecimal t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(BigInteger t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Double t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Float t) {
				return t;
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Long t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Integer t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Short t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF).floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(Byte t) {
				return t.floatValue();
			}});
		map.put(Float.class.getName(), new Function<Object, Float>() {
			@Override
			public Float apply(String t) {
				return Float.valueOf(t);
			}});

		// Long to others
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(BigDecimal t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(BigInteger t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Double t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Float t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Long t) {
				return t;
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Integer t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Short t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF).longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(Byte t) {
				return t.longValue();
			}});
		map.put(Long.class.getName(), new Function<Object, Long>() {
			@Override
			public Long apply(String t) {
				return Long.valueOf(t);
			}});

		// Integer to others
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(BigDecimal t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(BigInteger t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Double t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Float t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Long t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Integer t) {
				return t;
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Short t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF);
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(Byte t) {
				return t.intValue();
			}});
		map.put(Integer.class.getName(), new Function<Object, Integer>() {
			@Override
			public Integer apply(String t) {
				return Integer.valueOf(t);
			}});

		// Short to others
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(BigDecimal t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(BigInteger t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Double t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Float t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Long t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Integer t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Short t) {
				return t;
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF).shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(Byte t) {
				return t.shortValue();
			}});
		map.put(Short.class.getName(), new Function<Object, Short>() {
			@Override
			public Short apply(String t) {
				return Short.valueOf(t);
			}});

		// Character to others
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(BigDecimal t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(BigInteger t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Double t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Float t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Long t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Integer t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Short t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Character t) {
				return t;
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(Byte t) {
				return Character.valueOf((char)t.intValue());
			}});
		map.put(Character.class.getName(), new Function<Object, Character>() {
			@Override
			public Character apply(String t) {
				// かいざん
				// TODO:
				return t.charAt(0);
			}});

		// Byte to others
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(BigDecimal t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(BigInteger t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Double t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Float t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Long t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Integer t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Short t) {
				return t.byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Character t) {
				return Integer.valueOf(((int)t) & 0x0FFFF).byteValue();
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(Byte t) {
				return t;
			}});
		map.put(Byte.class.getName(), new Function<Object, Byte>() {
			@Override
			public Byte apply(String t) {
				//TODO:
				return Integer.valueOf(t).byteValue();
			}});
		// Double to others
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
		// BigDecimal to others
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(BigDecimal t) {
				return t;
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(BigInteger t) {
				//Why Exact , Exact 使うと例外が出るので、キャストの目的としては
				// 普通の longValue でOK
				return BigDecimal.valueOf(t.longValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Double t) {
				return BigDecimal.valueOf(t.doubleValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Float t) {
				return BigDecimal.valueOf(t.floatValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Long t) {
				return BigDecimal.valueOf(t.longValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Integer t) {
				return BigDecimal.valueOf(t.longValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Short t) {
				return BigDecimal.valueOf(t.longValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Character t) {
				return BigDecimal.valueOf(Long.valueOf(((long)t) & 0x0FFFF).longValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(Byte t) {
				return BigDecimal.valueOf(t.byteValue());
			}});
		map.put(BigDecimal.class.getName(), new Function<Object, BigDecimal>() {
			@Override
			public BigDecimal apply(String t) {
				return new BigDecimal(t);
			}});
		// BigInteger to others
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(BigDecimal t) {
				return t.toBigInteger();
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(BigInteger t) {
				return t;
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Double t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Float t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Long t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Integer t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Short t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Character t) {
				return BigInteger.valueOf(Long.valueOf(((long)t) & 0x0FFFF).longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(Byte t) {
				return BigInteger.valueOf(t.longValue());
			}});
		map.put(BigInteger.class.getName(), new Function<Object, BigInteger>() {
			@Override
			public BigInteger apply(String t) {
				return new BigInteger(t);
			}});
*/
	};
}
