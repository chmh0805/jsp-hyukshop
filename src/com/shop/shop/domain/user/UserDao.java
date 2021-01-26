package com.shop.shop.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.shop.config.DB;
import com.shop.shop.domain.user.dto.JoinUser;
import com.shop.shop.domain.user.dto.KakaoJoinUser;
import com.shop.shop.domain.user.dto.NaverJoinUser;
import com.shop.shop.domain.user.dto.UpdateUser;

public class UserDao {
	
	public User findByUserIdPassword(int userId, String password) {
		String sql = "SELECT id, username, name, email, phone, address, password FROM user WHERE id = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User userEntity = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.password(rs.getString("password"))
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
	
	public int update(UpdateUser updateUser) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE user ");
		sb.append("SET email = ?, phone = ?, address = ?, password = ? ");
		sb.append("WHERE id = ?");
		String sql = sb.toString();
		PreparedStatement pstmt = null;
		Connection conn = DB.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateUser.getEmail());
			pstmt.setString(2, updateUser.getPhone());
			pstmt.setString(3, updateUser.getAddress());
			pstmt.setString(4, updateUser.getPassword());
			pstmt.setInt(5, updateUser.getId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int insert(NaverJoinUser naverJoinUser) {
		String sql = "INSERT INTO user(name, email, phone, address, password, naverId, createDate) VALUES (?, ?, ?, ?, ?, ?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, naverJoinUser.getName());
			pstmt.setString(2, naverJoinUser.getEmail());
			pstmt.setString(3, naverJoinUser.getPhone());
			pstmt.setString(4, naverJoinUser.getAddress());
			pstmt.setString(5, naverJoinUser.getPassword());
			pstmt.setLong(6, naverJoinUser.getNaverId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int insert(KakaoJoinUser kakaoJoinUser) {
		String sql = "INSERT INTO user(name, email, phone, address, password, kakaoId, createDate) VALUES (?, ?, ?, ?, ?, ?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kakaoJoinUser.getName());
			pstmt.setString(2, kakaoJoinUser.getEmail());
			pstmt.setString(3, kakaoJoinUser.getPhone());
			pstmt.setString(4, kakaoJoinUser.getAddress());
			pstmt.setString(5, kakaoJoinUser.getPassword());
			pstmt.setLong(6, kakaoJoinUser.getKakaoId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int insert(JoinUser joinUser) {
		String sql = "INSERT INTO user(name, username, email, phone, address, password, createDate) VALUES (?, ?, ?, ?, ?, ?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinUser.getName());
			pstmt.setString(2, joinUser.getUsername());
			pstmt.setString(3, joinUser.getEmail());
			pstmt.setString(4, joinUser.getPhone());
			pstmt.setString(5, joinUser.getAddress());
			pstmt.setString(6, joinUser.getPassword());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public User login(String username, String password) {
		String sql = "SELECT id, username, name, auth FROM user WHERE username = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User userEntity = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.name(rs.getString("name"))
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