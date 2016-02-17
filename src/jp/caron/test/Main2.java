package jp.caron.test;

import jp.caron.util.ArgSet;
import jp.caron.util.InjectUtil;
import jp.caron.util.PropertyListener;

public class Main2 {

	public static class Dto1 {
		public Dto1(String s) {
			this.s = s;
		}
		String s;
		public void setS(String s) {
			this.s = s;
		}
		public String getS() {
			return s;
		}

	}
	public static void main(String[] args) {

		PropertyListener pl = InjectUtil.newPropertyListener();

		Dto1 dto1 = new Dto1("OK");

		System.out.println("getProp::" + pl.prop(dto1, "s"));

		pl.prop(dto1, "s", "PPP");
		System.out.println("setProp('PPP') and getProp::" + pl.prop(dto1, "s"));

		System.out.println("invoke('getS') returns ::" + pl.call(dto1, "getS"));

		pl.call(dto1, "setS", new ArgSet<String>(String.class, "OKNG"));

		System.out.println("invoke('getS') returns ::" + pl.call(dto1, "getS"));

	}

}
