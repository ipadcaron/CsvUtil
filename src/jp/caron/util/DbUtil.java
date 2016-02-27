package jp.caron.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	/**
	 * プレースホルだアノテーション
	 */
	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Ph {
		String name() default "";
		Class<?> type() default String.class;
		boolean nullable() default true;
	}

	static class DbManagerImpl implements DbManager {
		Connection con;

		@Override
		public void close() throws Exception {
			if (con != null) {
				con.close();
			}
		}

		@Override
		public int insert(String sql, Object... params) throws SQLException {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public int update(String sql, @Ph Object... params) throws SQLException {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public int delete(String sql, Object... params) throws SQLException {


			return 0;
		}

		@Override
		public void autoCommit(boolean auto) throws SQLException {
			con.setAutoCommit(auto);
		}
	}

	public interface DbManager extends AutoCloseable {

		void autoCommit(boolean auto) throws SQLException;
		int insert(String sql, Object... params) throws SQLException;
		int update(String sql, @Ph Object... params) throws SQLException;
		int delete(String sql, Object... params) throws SQLException;

		default DbManager create(String url) throws SQLException {
			DbManagerImpl impl = new DbManagerImpl();

			impl.con = DriverManager.getConnection(url);

			impl.con.setAutoCommit(true);

			String ok = "STEING";
			String address = "juusho";
			impl.insert("@test1", ok, address );


			return impl;
		}
	}
}
