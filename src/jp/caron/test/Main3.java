package jp.caron.test;

import jp.caron.util.InjectUtil;

public class Main3 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {

		TestDto dto1 = new TestDto(33, 34.56F, 56.78D);

		TestDto dto2 = InjectUtil.copyBean(dto1);

		System.out.println(dto1 + "=>>" + dto2);
	}

}
