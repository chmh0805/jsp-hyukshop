package com.shop.shop.domain.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.cart.dto.CartAllDto;
import com.shop.shop.domain.cart.dto.CartDto;

public class CartDao {
	
	public List<Integer> findFavorByUserId(int userId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT productId FROM favorite WHERE userId = ?";
		List<Integer> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getInt("productId"));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<CartAllDto> findByUserId(int userId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT cart.id, company.name, cart.productId, product.productName, product.price, product.imgUrl_1 ");
		sb.append("FROM cart INNER JOIN product ON cart.productId = product.id ");
		sb.append("INNER JOIN company ON product.companyId = company.id WHERE userId = ?");
		String sql = sb.toString();
		List<CartAllDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartAllDto dto = CartAllDto.builder()
						.id(rs.getInt("cart.id"))
						.companyName(rs.getString("company.name"))
						.productId(rs.getInt("cart.productId"))
						.productName(rs.getString("product.productName"))
						.price(rs.getLong("product.price"))
						.imgUrl_1(rs.getString("product.imgUrl_1"))
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
	
	public boolean isCart(int userId, int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cart WHERE userId = ? AND productId = ?";
		
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
	
	public int addCart(CartDto cartDto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO cart(userId, productId) VALUES (?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getUserId());
			pstmt.setInt(2, cartDto.getProdId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public int rmvCart(CartDto cartDto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM cart WHERE userId = ? AND productId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartDto.getUserId());
			pstmt.setInt(2, cartDto.getProdId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
