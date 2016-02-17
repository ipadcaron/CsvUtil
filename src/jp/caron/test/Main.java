package jp.caron.test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import jp.caron.util.CsvReader;
import jp.caron.util.InjectUtil;

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

		public void setValNam(int val, String nam) {
			this.val = val;
			this.name = nam;
		}
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


		try {
			int[] array = InjectUtil.newInstance(int[].class, 3);
			for (int i=0;i < array.length;i++) array[i] = i;

			for (int i=0;i < array.length;i++) {
				System.out.printf("instanced array[%d]=%d%n", i, i);
			}

			Dto1 sto = InjectUtil.newInstance(Dto1.class);

			sto.setName("OK");
			sto.setVal(35);
			System.out.println(sto);

			int[][] arr2 = InjectUtil.newInstance(int[][].class, 2,2);

			for (int y=0;y < arr2.length;y++)
				for (int x=0;x < arr2[y].length;x++)
					arr2[y][x] = y*10 + x;

			for (int y=0;y < arr2.length;y++)
				for (int x=0;x < arr2[y].length;x++)
					System.out.printf("arr2[%d][%d] = %d%n", y, x, arr2[y][x]);


		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
