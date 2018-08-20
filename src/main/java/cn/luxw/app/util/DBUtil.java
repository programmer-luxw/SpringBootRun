//package cn.luxw.app.util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.dbutils.BasicRowProcessor;
//import org.apache.commons.dbutils.BeanProcessor;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.ArrayHandler;
//import org.apache.commons.dbutils.handlers.ArrayListHandler;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;
//import org.apache.commons.dbutils.handlers.ColumnListHandler;
//import org.apache.commons.dbutils.handlers.KeyedHandler;
//import org.apache.commons.dbutils.handlers.MapHandler;
//import org.apache.commons.dbutils.handlers.MapListHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import cn.luxw.app.entity.User;
//
//public class DBUtil {
//
//	private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
//
//	/**
//	 * 查询（返回 Array）
//	 * 
//	 * @param runner
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static Object[] queryArray(String sql, Object... params) {
//		Object[] result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new ArrayHandler(), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询（返回 ArrayList）
//	 * 
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static List<Object[]> queryArrayList(String sql, Object... params) {
//		List<Object[]> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new ArrayListHandler(), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询（返回 Map）
//	 * 
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static Map<String, Object> queryMap(String sql, Object... params) {
//		Map<String, Object> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new MapHandler(), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询（返回 MapList）
//	 * 
//	 * @param runner
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
//		List<Map<String, Object>> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new MapListHandler(), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询（返回 Bean）
//	 * 
//	 * @param clazz
//	 * @param map
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static <T> T queryBean(Class<T> clazz, Map<String, String> map, String sql, Object... params) {
//		T result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			if (MapUtils.isNotEmpty(map)) {
//				result = runner.query(sql, new BeanHandler<T>(clazz, new BasicRowProcessor(new BeanProcessor(map))), params);
//			} else {
//				result = runner.query(sql, new BeanHandler<T>(clazz), params);
//			}
//			printSQL(sql);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	/**
//	 * 查询（返回 BeanList）
//	 * 
//	 * @param clazz
//	 * @param map
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static <T> List<T> queryBeanList(Class<T> clazz, Map<String, String> map, String sql, Object... params) {
//		List<T> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			if (MapUtils.isNotEmpty(map)) {
//				result = runner.query(sql, new BeanListHandler<T>(clazz, new BasicRowProcessor(new BeanProcessor(map))), params);
//			} else {
//				result = runner.query(sql, new BeanListHandler<T>(clazz), params);
//			}
//			printSQL(sql);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	/**
//	 * 查询指定列名的值（单条数据）
//	 * 
//	 * @param column
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static Object queryColumn(String column, String sql, Object... params) {
//		Object result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new ScalarHandler<Object>(column), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询指定列名的值（多条数据）
//	 * 
//	 * @param column
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static <T> List<T> queryColumnList(String column, String sql, Object... params) {
//		List<T> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new ColumnListHandler<T>(column), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 查询指定列名对应的记录映射
//	 * 
//	 * @param column
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static <T> Map<T, Map<String, Object>> queryKeyMap(String column, String sql, Object... params) {
//		Map<T, Map<String, Object>> result = null;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			result = runner.query(sql, new KeyedHandler<T>(column), params);
//		} catch (SQLException e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		printSQL(sql);
//		return result;
//	}
//
//	/**
//	 * 更新（包括 UPDATE、INSERT、DELETE，返回受影响的行数）
//	 * 
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static int update(String sql, Object... params) {
//		int result = 0;
//		try {
//			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
//			JdbcUtil.begin();
//			result = runner.update(sql, params);
//			printSQL(sql);
//			JdbcUtil.commit();
//		} catch (SQLException e) {
//			JdbcUtil.rollback();
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		} finally {
//			JdbcUtil.close();
//		}
//		return result;
//	}
//
//	private static void printSQL(String sql) {
//		if (logger.isInfoEnabled()) {
//			logger.info("SQL: " + sql);
//		}
//	}
//
//	public static void updatePackage(Map<Long, User> appMap) {
//		if (MapUtils.isEmpty(appMap)){
//			return;
//		}
//		Connection connection = JdbcUtil.getConnection();
//		PreparedStatement ps = null;
//		String sql = "UPDATE `app_basic` SET packageName = ? WHERE packageName = ? AND source=2 ";
//		try {
//			connection.setAutoCommit(false);
//			ps = connection.prepareStatement(sql);
//			int count = 0;
//			for (User app : appMap.values()) {
//				//logger.debug("packageName={}", app.getName());
//				count++;
//				//ps.setString(1, app.getName());
//				//ps.setString(2, app.getName());
//				ps.addBatch();
//				if (count % 300 == 0) {
//					ps.executeBatch();
//					connection.commit();
//					ps.clearBatch();
//				}
//
//			}
//			ps.executeBatch();
//			connection.commit();
//			ps.clearBatch();
//		} catch (SQLException e) {
//			logger.error("sql exception", e);
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				logger.error("rollback exception", e);
//			}
//		} finally {
//			try {
//				if (ps != null)
//					ps.close();
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//				logger.error("close resource exception", e);
//			}
//
//		}
//	}
//}
