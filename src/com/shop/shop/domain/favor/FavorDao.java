package com.shop.shop.domain.favor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.favor.dto.FavorCompListReqDto;
import com.shop.shop.domain.favor.dto.FavorDto;
import com.shop.shop.domain.favor.dto.FavorReqDto;

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
	
	public List<FavorReqDto> findByUserId(int userId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT f.id, p.id, p.productName, c.name, p.price, p.soldCount, p.imgUrl_1 ");
		sb.append("FROM favorite f INNER JOIN product p ON f.productId = p.id ");
		sb.append("INNER JOIN company c ON p.companyId = c.id ");
		sb.append("WHERE f.userId = ?");
		String sql = sb.toString();
		List<FavorReqDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FavorReqDto dto = FavorReqDto.builder()
						.id(rs.getInt("f.id"))
						.productId(rs.getInt("p.id"))
						.productName(rs.getString("p.productName"))
						.companyName(rs.getString("c.name"))
						.price(rs.getLong("p.price"))
						.soldCount(rs.getInt("p.soldCount"))
						.imgUrl_1(rs.getString("imgUrl_1"))
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
	
	public List<FavorReqDto> sortByCompId(int userId, int compId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT f.id, p.id, p.productName, c.name, p.price, p.soldCount, p.imgUrl_1 ");
		sb.append("FROM favorite f INNER JOIN product p ON f.productId = p.id ");
		sb.append("INNER JOIN company c ON p.companyId = c.id ");
		sb.append("WHERE f.userId = ? AND c.id = ?");
		String sql = sb.toString();
		List<FavorReqDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FavorReqDto dto = FavorReqDto.builder()
						.id(rs.getInt("f.id"))
						.productId(rs.getInt("p.id"))
						.productName(rs.getString("p.productName"))
						.companyName(rs.getString("c.name"))
						.price(rs.getLong("p.price"))
						.soldCount(rs.getInt("p.soldCount"))
						.imgUrl_1(rs.getString("imgUrl_1"))
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
	
	public List<FavorCompListReqDto> findCompListByUserId(int userId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.name FROM favorite f INNER JOIN product p ON f.productId = p.id ");
		sb.append("INNER JOIN company c ON p.companyId = c.id WHERE f.userId = ?");
		String sql = sb.toString();
		List<FavorCompListReqDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FavorCompListReqDto dto = FavorCompListReqDto.builder()
						.companyId(rs.getInt("c.id"))
						.companyName(rs.getString("c.name"))
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
