package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spiderworts.util.n1.print.P;

public class LogOtherTest {

	private static Logger logger = LoggerFactory.getLogger(LogOtherTest.class);

	@Test
	public void test1() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("net.sf.log4jdbc.DriverSpy"); // log4jdbc4.jar提供的类
			conn = DriverManager.getConnection("jdbc:log4jdbc:mysql://127.0.0.1:3306/wk_developing", "root", "123456");
			pstmt = conn.prepareStatement("select name from user_p1 where date > ?");
			pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				logger.info(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
	}
}