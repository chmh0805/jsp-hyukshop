package com.shop.shop.domain.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.qna.dto.AllQnaDto;
import com.shop.shop.domain.qna.dto.DetailQnaDto;
import com.shop.shop.domain.qna.dto.DetailQnaRespDto;
import com.shop.shop.domain.qna.dto.InsertQnaDto;

public class QnaDao {
	
	public List<AllQnaDto> findForAll(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT q.id, q.detail, q.password, q.optionNo, q.createDate, u.name FROM qna q INNER JOIN user u ON q.userId = u.id WHERE q.productId = ? ORDER BY q.id DESC";
		List<AllQnaDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllQnaDto dto = AllQnaDto.builder()
						.id(rs.getInt("q.id"))
						.detail(rs.getString("q.detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.password(rs.getString("q.password"))
						.optionNo(rs.getInt("q.optionNo"))
						.createDate(rs.getTimestamp("q.createDate"))
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
	
	public List<DetailQnaRespDto> findForDetail(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT q.id, q.detail, q.password, q.createDate, u.name FROM qna q INNER JOIN user u ON q.userId = u.id WHERE q.productId = ? ORDER BY q.id DESC LIMIT 5";
		List<DetailQnaRespDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailQnaRespDto dto = DetailQnaRespDto.builder()
						.id(rs.getInt("q.id"))
						.detail(rs.getString("q.detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.password(rs.getString("q.password"))
						.createDate(rs.getTimestamp("q.createDate"))
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
		String sql = "SELECT id, detail, password, createDate FROM qna WHERE productId = ? ORDER BY id DESC LIMIT 5";
		List<DetailQnaDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailQnaDto dto = DetailQnaDto.builder()
						.id(rs.getInt("id"))
						.detail(rs.getString("detail").replaceAll("<p>", "").replaceAll("</p>", ""))
						.password(rs.getString("password"))
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
	
	public DetailQnaDto findById(int id) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, detail, password, optionNo, createDate FROM qna WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				DetailQnaDto result = DetailQnaDto.builder()
						.id(rs.getInt("id"))
						.detail(rs.getString("detail"))
						.password(rs.getString("password"))
						.optionNo(rs.getInt("optionNo"))
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
