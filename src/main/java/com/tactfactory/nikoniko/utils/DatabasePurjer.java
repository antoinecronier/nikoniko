package com.tactfactory.nikoniko.utils;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;

public class DatabasePurjer {

	public static void purjeDatabase() {
		String deleter = "DELETE FROM ";
		String table = "";

		table = "team_project";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		table = "user_team";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		table = "nikoniko";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		table = "user";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		table = "project";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		table = "team";
		MySQLAccess.getInstance().updateQuery(deleter + table);

		// ResultSet query = MySQLAccess
		// .getInstance()
		// .resultQuery(
		// "SELECT table_name "
		// + "FROM information_schema.tables "
		// + "where table_schema='"+MySQLAccess.DATABASE+"';");
		// try {
		// while (query.next()) {
		// String deleter = "";
		// deleter += "DELETE FROM " +query.getString(1)+";";
		// MySQLAccess
		// .getInstance()
		// .updateQuery(deleter);
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
