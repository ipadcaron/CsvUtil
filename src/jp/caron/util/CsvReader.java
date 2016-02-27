package jp.caron.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	 * @param charSet
	 * @throws IOException
	 */
	private CsvReader(File path, Charset charSet) throws IOException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charSet));
	}

	/**
	 * SJIS encoding constructor
	 * @param path
	 * @throws IOException
	 */
	private CsvReader(File path) throws IOException {
		this(path, Charset.forName("Shift_JIS"));
	}

	public <T> Iterable<T> list(boolean isHeader, Class<? super T> clazz) throws InstantiationException, IllegalAccessException {

		return new InnerIterable<T>(isHeader, clazz, this.br, InjectUtil.newPropertyListener());
	}

	public <T> Iterable<T> list(boolean isHeader, Class<? super T> clazz, PropertyListener pl) throws InstantiationException, IllegalAccessException {

		return new InnerIterable<T>(isHeader, clazz, this.br, pl);
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

	static class InnerIterable<T> implements Iterable<T> {

		private Class<? super T> clazz;
		private BufferedReader br;
		private boolean isHeader;
		private int dummy;
		private PropertyListener pl;
		public InnerIterable(boolean isHeader, Class<? super T> clazz, BufferedReader br, PropertyListener pl) {
			this.isHeader = isHeader;
			this.clazz = clazz;
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
					T instance = pl.newArrayInstance(clazz);
					pl.call(instance, "setValNam", new ArgSet<Integer>(int.class, 3456 + dummy), new ArgSet<String>(String.class, "Hello World" + dummy));
					return instance;
				}
			};
		}
	}

	public static List<String> readLine(BufferedReader br, char delim) throws IOException {

		List<String> list = new ArrayList<String>();

		String line = null;
		CharReader cr = new CharReader();
		StringBuilder sb = new StringBuilder(1000);
		boolean quote = false;

		while ((line  = br.readLine()) != null) {

			cr.reset(line);
			
			while (cr.hasNext()) {

				char ch = cr.next();
				
				if (quote) {
					if (ch == '"') {
						if (cr.hasNext()) {
							if (cr.next() == '"') {
								sb.append('"');
								continue;
							} else {
								cr.back();
								quote = false;
								list.add(sb.toString());
								sb.setLength(0);
								continue;
							}
						}
					} else {
						sb.append(ch);
						continue;
					}
				}
				
				if (ch == '"') {
					quote = true;
					continue;
				}
			}
			


		}
		return list;
	}


}
