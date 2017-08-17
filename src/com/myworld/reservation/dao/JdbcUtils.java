package com.myworld.reservation.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
/**
 * jdbc工具类
 * @author gg
 *
 */
public class JdbcUtils {
	static Properties props = new Properties();
	static{
		InputStream in = JdbcUtils.class.getResourceAsStream("/database.properties");
		try {
			props.load(in);
		} catch (IOException e) {
			System.out.println("读取数据源属性文件失败：" + e.getMessage());
		}
		DbUtils.loadDriver(getValue("jdbc.driver"));
	}

	//由于要用静态获得值，必须放在这里
	private static String url = getValue("jdbc.url");
	private static String user = getValue("jdbc.user");
	private static String password = getValue("jdbc.password");
	
	/**
	 * 获得数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("获得数据连接失败：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据键名获得数据库属性值
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return props.getProperty(key);
	}
}
