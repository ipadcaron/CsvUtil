package jp.caron.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public class CsvReader implements Closeable {

	private BufferedReader br;

	/**
	 * new instance by SJIS reader
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static CsvReader newShiftJisReader(File path) throws IOException {
		return new CsvReader(path);
	}

	/**
	 * new instance by utf-8 reader
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static CsvReader newUtf8Reader(File path) throws IOException {
		return new CsvReader(path, Charset.forName("utf-8"));
	}

	/**
	 * constructor
	 * @param path
	 * @param set
	 * @throws IOException
	 */
	private CsvReader(File path, Charset set) throws IOException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path), set));
	}

	/**
	 * SJIS encoding constructor
	 * @param path
	 * @throws IOException
	 */
	private CsvReader(File path) throws IOException {
		this(path, Charset.forName("Shift_JIS"));
	}

	@SuppressWarnings("unchecked")
	public <T> Iterable<T> list(boolean isHeader, Class<? super T> clazz) throws InstantiationException, IllegalAccessException {

		return new InnerIterable<T>(isHeader, (T) clazz.newInstance(), this.br, InjectUtil.newPropertyListener());
	}

	@SuppressWarnings("unchecked")
	public <T> Iterable<T> list(boolean isHeader, Class<? super T> clazz, PropertyListener pl) throws InstantiationException, IllegalAccessException {

		return new InnerIterable<T>(isHeader, (T) clazz.newInstance(), this.br, pl);
	}

	public Iterable<Map<String, Object>> list(boolean isHeader) {
		return null;
	}



	@Override
	public void close() throws IOException {
		if (br != null) {
			br.close();
		}
	}

	public static class InnerIterable<T> implements Iterable<T> {

		private T instance;
		private BufferedReader br;
		private boolean isHeader;
		private int dummy;
		private PropertyListener pl;
		public InnerIterable(boolean isHeader, T instance, BufferedReader br, PropertyListener pl) {
			this.isHeader = isHeader;
			this.instance = instance;
			this.br = br;
			this.dummy = 0;
			this.pl = pl;
		}

		@Override
		public Iterator<T> iterator() {
			return new Iterator<T>() {

				@Override
				public boolean hasNext() {
					dummy++;
					return dummy < 5 ? true : false;
				}

				@Override
				public T next() {
					pl.setProperty(instance, "val", dummy);
					pl.setProperty(instance, "name", dummy + "PL");
					return instance;
				}
			};
		}
	}
}
