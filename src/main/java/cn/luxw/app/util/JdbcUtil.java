//package cn.luxw.app.util;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
////JDBC帮助类[含事务处理]
//public final class JdbcUtil {
//	private static final Logger logger = LoggerFactory.getLogger(JdbcUtil.class);
//
//	private static ComboPooledDataSource dataSource;
//	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
//	static {
//		dataSource = new ComboPooledDataSource();
//	}
//
//	public static ComboPooledDataSource getDataSource() {
//		return dataSource;
//	}
//
//	public static Connection getConnection() {
//		Connection conn = tl.get();
//		if (conn == null) {
//			try {
//				conn = dataSource.getConnection();
//			} catch (SQLException e) {
//				logger.error(e.getMessage(), e);
//				throw new RuntimeException(e.getMessage(), e);
//			}
//			tl.set(conn);
//		}
//		return conn;
//	}
//
//	public static void begin() {
//		Connection conn = getConnection();
//		try {
//			conn.setAutoCommit(false);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//	}
//
//	public static void commit() {
//		Connection conn = getConnection();
//		try {
//			conn.commit();
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//	}
//
//	public static void rollback() {
//		Connection conn = getConnection();
//		try {
//			conn.rollback();
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//	}
//
//	public static void close() {
//		Connection conn = getConnection();
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		tl.remove();
//	}
//}
