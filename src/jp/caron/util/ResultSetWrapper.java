package jp.caron.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetWrapper {

	private ResultSet rs;

	public ResultSetWrapper(ResultSet rs) {
		this.rs = rs;
	}

	public static RsWrapper newRs(ResultSet rs) {
		return new RsWrapper(rs) {

			@Override
			public String[] labels() throws SQLException {
				return super.getFields();
			}

			@Override
			public int rows() throws SQLException {
				return super.rs.getRow();
			}

			@Override
			public void setup() throws SQLException {
				super.rs.first();
			}

			@Override
			public Object next() throws SQLException {
				if (rs.next()) {
					rs.getObject(0);
				}
				return null;
			}

		};
	}
}
