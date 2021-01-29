package com.shop.shop.domain.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.review.dto.DetailReviewRespDto;
import com.shop.shop.domain.review.dto.DetailReviewDto;
import com.shop.shop.domain.review.dto.InsertReviewDto;

public class ReviewDao {
	
	public List<DetailReviewRespDto> findForMine(int userId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT r.id, r.detail, r.createDate, u.name FROM review r INNER JOIN user u ON r.userId = u.id WHERE r.userId = ? ORDER BY r.id DESC";
		List<DetailReviewRespDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailReviewRespDto dto = DetailReviewRespDto.builder()
						.id(rs.getInt("r.id"))
						.detail(rs.getString("r.detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.createDate(rs.getTimestamp("r.createDate"))
						.name(rs.getString("u.name"))
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
	
	public List<DetailReviewRespDto> findForAll(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT r.id, r.detail, r.createDate, u.name FROM review r INNER JOIN user u ON r.userId = u.id WHERE r.productId = ? ORDER BY r.id DESC";
		List<DetailReviewRespDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailReviewRespDto dto = DetailReviewRespDto.builder()
						.id(rs.getInt("r.id"))
						.detail(rs.getString("r.detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.createDate(rs.getTimestamp("r.createDate"))
						.name(rs.getString("u.name"))
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
	
	public List<DetailReviewRespDto> findForDetail(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT r.id, r.detail, r.createDate, u.name FROM review r INNER JOIN user u ON r.userId = u.id WHERE r.productId = ? ORDER BY r.id DESC LIMIT 5";
		List<DetailReviewRespDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailReviewRespDto dto = DetailReviewRespDto.builder()
						.id(rs.getInt("r.id"))
						.detail(rs.getString("r.detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.createDate(rs.getTimestamp("r.createDate"))
						.name(rs.getString("u.name"))
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
	
	public int insert(InsertReviewDto dto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO review(userId, productId, detail, createDate) VALUES (?, ?, ?, now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getProductId());
			pstmt.setString(3, dto.getDetail());
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
		String sql = "SELECT count(id) FROM review WHERE productId = ?";
		
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
	
	public List<DetailReviewDto> findByProdNo(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, detail, createDate FROM review WHERE productId = ? ORDER BY id DESC LIMIT 5";
		List<DetailReviewDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailReviewDto dto = DetailReviewDto.builder()
						.id(rs.getInt("id"))
						.detail(rs.getString("detail").replaceAll("<p>", "").replaceAll("</p>", ""))
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
	
	public DetailReviewDto findById(int id) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, detail, createDate FROM review WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				DetailReviewDto result = DetailReviewDto.builder()
						.id(rs.getInt("id"))
						.detail(rs.getString("detail"))
						.createDate(rs.getTimestamp("createDate"))
						.build();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
}
