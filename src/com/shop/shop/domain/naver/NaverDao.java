package com.shop.shop.domain.naver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.shop.config.DB;
import com.shop.shop.domain.user.User;

public class NaverDao {

	public User findByNaverId(long naverId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, username, name, auth FROM user WHERE naverId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, naverId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User userEntity = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.name(rs.getString("name"))
						.auth(rs.getString("auth"))
						.build();
				return userEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
}
