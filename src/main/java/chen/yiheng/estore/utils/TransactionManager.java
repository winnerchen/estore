package chen.yiheng.estore.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	public static Connection getCurrentConnection() {
		Connection conn = local.get();
		if (conn == null) {
			conn = JDBCUtils.getConnection();
			local.set(conn);
		}
		return conn;
	}

	public static void begin() {
		Connection conn = getCurrentConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		Connection conn = getCurrentConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commit(){
		Connection conn=getCurrentConnection();
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			local.remove();
		}
	}

}
