package com.shop.shop.domain.favor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.shop.config.DB;
import com.shop.shop.domain.product.dto.FavorDto;

public class FavorDao {
	
	public boolean isFavor(int userId, int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM favorite WHERE userId = ? AND productId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, prodNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return false;
	}
	
	public int addFavor(FavorDto favorDto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO favorite(userId, productId) VALUES (?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, favorDto.getUserId());
			pstmt.setInt(2, favorDto.getProdId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int rmvFavor(FavorDto favorDto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM favorite WHERE userId = ? AND productId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, favorDto.getUserId());
			pstmt.setInt(2, favorDto.getProdId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
