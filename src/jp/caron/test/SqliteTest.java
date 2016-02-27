package jp.caron.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		try {
			// JDBCドライバーの指定
			Class.forName("org.sqlite.JDBC").newInstance();

			// データベースに接続する なければ作成される
			Connection con = DriverManager
					.getConnection("jdbc:sqlite:./database.db.sqlite3");

			con.setAutoCommit(true);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM test1 order by id ASC");
			while (rs.next()) {
				Object o1 = rs.getObject(1);
				Object o2 = rs.getObject(2);
				System.out.printf("%-10s%-30s(%s,%s)%n", o1, o2, o1.getClass(), o2.getClass());
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			// Connection の例外が発生した時

			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
