package jp.caron.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TConv {




	/**
	 * 左辺型強制の代入
	 * @param rType
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <R> R gt(Class<R> rType, Object val) {
		return (R) to(rType, val);
	}

	/**
	 * o を rType にコンバートする
	 * char に　Number が付いてないので、 ? で曖昧にしてるが、
	 * primitive 対応クラス型を指定する、、、（ないとまずいことになる）
	 * 全パターン試してないけどこんなんで OK かな？
	 * not("o extends Number") のとき o をそのまま返す。
	 * よって、Number 実装クラスと以外は何も触らない。
	 * @param rType
	 * @param o
	 * @return
	 */
	public static Object to(Class<?> rType, Object o) {

		// void 型戻り値は何返してもオッケーなんだっけ？
		if (o == null || rType.equals(Void.class)) {
			return null;
		}

		if (rType.equals(String.class)) {
			return o.toString();
		}

		Object retVal = null;

		if (o instanceof Number) {
			String os = rType.getName();
			String tmapVal = tmap.get(os);
			Function<Number, ?> fn;
			if (tmapVal != null)
				fn = cmap.get(tmapVal);
			else
				fn = cmap.get(os);

			retVal = fn.apply((Number) o);
		} else if (o instanceof Character) {
			String os = rType.getName();
			String tmapVal = tmap.get(os);
			Function<Number, ?> fn;
			if (tmapVal != null)
				fn = cmap.get(tmapVal);
			else
				fn = cmap.get(os);

			retVal = fn.apply(Integer.valueOf((Character)o).intValue());
		} else {
			retVal = o;
		}

		return retVal;
	}

	private static Map<String, Function<Number, ?>> cmap;
	private static Map<String, String> tmap;

	static {
		// クラス型変換マップ
		tmap = new HashMap<>();
		tmap.put(Byte.class.getName(), byte.class.getName());
		tmap.put(Character.class.getName(), char.class.getName());
		tmap.put(Short.class.getName(), short.class.getName());
		tmap.put(Integer.class.getName(), int.class.getName());
		tmap.put(Long.class.getName(), long.class.getName());
		tmap.put(Float.class.getName(), float.class.getName());
		tmap.put(Double.class.getName(), double.class.getName());
		tmap.put(BigDecimal.class.getName(), BigDecimal.class.getName());
		tmap.put(BigInteger.class.getName(), BigInteger.class.getName());


		// 型変換実装マップ
		cmap = new HashMap<>();
		cmap.put(byte.class.getName(), new Function<Number, Byte>() {
			@Override
			public Byte apply(Number t) {
				return t.byteValue();
			}});
		cmap.put(char.class.getName(), new Function<Number, Character>() {
			@Override
			public Character apply(Number t) {
				return (char) t.intValue();
			}});
		cmap.put(short.class.getName(), new Function<Number, Short>() {
			@Override
			public Short apply(Number t) {
				return t.shortValue();
			}});
		cmap.put(int.class.getName(), new Function<Number, Integer>() {
			@Override
			public Integer apply(Number t) {
				return t.intValue();
			}});
		cmap.put(long.class.getName(), new Function<Number, Long>() {
			@Override
			public Long apply(Number t) {
				return t.longValue();
			}});
		cmap.put(float.class.getName(), new Function<Number, Float>() {
			@Override
			public Float apply(Number t) {
				return t.floatValue();
			}});
		cmap.put(double.class.getName(), new Function<Number, Double>() {
			@Override
			public Double apply(Number t) {
				return t.doubleValue();
			}});
		cmap.put(BigDecimal.class.getName(), new Function<Number, BigDecimal>() {
			@Override
			public BigDecimal apply(Number t) {
				if (t instanceof BigDecimal) {
					return (BigDecimal) t;
				}
				return BigDecimal.valueOf(t.doubleValue());
			}});
		cmap.put(BigInteger.class.getName(), new Function<Number, BigInteger>() {
			@Override
			public BigInteger apply(Number t) {
				if (t instanceof BigInteger) {
					return (BigInteger) t;
				}
				return BigInteger.valueOf(t.longValue());
			}});

	};

	public void test(String title, Class<?> rType, Object val) {
		if (val != null) {
			Object o;
			System.out.printf("%s :: %s >>> %s <<== %s%n", title, val.getClass(), o = TConv.to(rType, val), o.getClass());
		} else {
			System.out.printf("%s :: %s :: %s%n", title, "(val is null)", TConv.to(rType, val));
		}
	}

	public static void main(String[] args) {
		TConv tc = new TConv();

		tc.test("null -> ", Void.class, null);
		tc.test("123 -> ", Integer.class, 123);
		tc.test("123D -> ", Integer.class, 123D);
		tc.test("123F -> ", Integer.class, 123F);
		tc.test("'E' -> ", Character.class, 'E');
		tc.test("'E' -> ", int.class, 'E');
		tc.test("3.14f -> ", Float.class, 3.14F);
		tc.test("2.41435D -> ", Double.class, 2.41435D);
		tc.test("1234567890123456 Bi -> ", BigInteger.class, BigInteger.valueOf(1234567890123456L));
		tc.test("1234567890123456 Bi -> ", Long.class, BigInteger.valueOf(1234567890123456L));

		tc.test("'Hello World -> ", String.class, "Hello World");
		tc.test("(int)12345 -> ", String.class, 12345);

		// クラスキャスト例外確定
		String ss = (String) TConv.to(String.class, 12345);

		Integer iVal = TConv.gt(Integer.class, BigDecimal.valueOf(12345L));
		System.out.println(iVal);
	}
}
