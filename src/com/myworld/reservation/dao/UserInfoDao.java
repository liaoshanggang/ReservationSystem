package com.myworld.reservation.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.myworld.reservation.vo.UserInfo;

public class UserInfoDao {
	QueryRunner runner = new QueryRunner();
	public UserInfo login(UserInfo userInfo){
		Connection conn = JdbcUtils.getConnection();
		//SELECT * FROM userinfo WHERE loginName ='admin' AND loginPass = 'admin';
		String sql = "SELECT * FROM userinfo WHERE loginName =? AND loginPass = ?";
		try {
			return runner.query(conn, sql, new BeanHandler<UserInfo>(UserInfo.class),userInfo.getLoginName(),userInfo.getLoginPass());
		} catch (SQLException e) {
			System.err.println("登录失败：" + e.getMessage());
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
}
