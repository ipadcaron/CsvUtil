package jp.caron.test;

import java.io.File;
import java.io.IOException;

import jp.caron.util.CsvReader;

public class Main {

	public static class Dto1 {
		public Integer getVal() {
			return val;
		}
		public void setVal(Integer val) {
			this.val = val;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private Integer val;
		private String name;
		@Override
		public String toString() {
			return "Dto1 [val=" + val + ", name=" + name + "]";
		}

	}

	public static void main(String...args) {

		try(CsvReader csv = CsvReader.newShiftJisReader(new File("test.csv"))) {

			for (Dto1 dto : csv.list(true, Dto1.class)) {

				System.out.println("Dto1::" + dto);
			}

		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}


	}
}
