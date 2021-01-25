package com.shop.shop.domain.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.qna.dto.DetailQnaDto;
import com.shop.shop.domain.qna.dto.InsertQnaDto;

public class QnaDao {
	
	public int insert(InsertQnaDto dto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO qna(userId, productId, optionNo, password, detail, createDate) VALUES (?, ?, ?, ?, ?, now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getProductId());
			pstmt.setInt(3, dto.getOptionNo());
			pstmt.setString(4, dto.getPassword());
			pstmt.setString(5, dto.getDetail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int countByProdId(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(id) FROM qna WHERE productId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("count(id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public List<DetailQnaDto> findByProdNo(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, detail, createDate FROM qna WHERE productId = ? ORDER BY id DESC LIMIT 5";
		List<DetailQnaDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailQnaDto dto = DetailQnaDto.builder()
						.id(rs.getInt("id"))
						.detail(rs.getString("detail").replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<img", ""))
						.createDate(rs.getTimestamp("createDate"))
						.build();
				result.add(dto);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
}
