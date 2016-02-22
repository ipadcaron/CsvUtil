package jp.caron.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class RsWrapper {
	protected ResultSet rs;
	public RsWrapper(ResultSet rs) {
		this.rs = rs;
	}
	public abstract String[] labels() throws SQLException;
	public abstract int rows() throws SQLException;
	public abstract void setup() throws SQLException;
	public abstract Object next() throws SQLException;

	protected String[] getFields() throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();

		String[] labels = new String[rsmd.getColumnCount()];

		for (int i=0;i < labels.length;i++) {
			labels[i] = rsmd.getColumnLabel(i);
		}

		return labels;
	}
}
