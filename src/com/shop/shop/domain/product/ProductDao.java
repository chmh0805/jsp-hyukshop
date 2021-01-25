package com.shop.shop.domain.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.product.dto.DetailProdRespDto;
import com.shop.shop.domain.product.dto.IndexDto;
import com.shop.shop.domain.product.dto.InsertReqDto;

public class ProductDao {
	
	public List<IndexDto> findAllWithLimitNum(int limitNum) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.id, p.productName, c.name, p.price, p.soldCount, p.imgUrl_1 FROM product p INNER JOIN company c ON p.companyId = c.id WHERE p.id < ?";
		
		List<IndexDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limitNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IndexDto indexDto = IndexDto.builder()
						.productId(rs.getInt("p.id"))
						.productName(rs.getString("p.productName"))
						.companyName(rs.getString("c.name"))
						.price(rs.getLong("p.price"))
						.soldCount(rs.getInt("p.soldCount"))
						.imgUrl_1(rs.getString("p.imgUrl_1"))
						.build();
				result.add(indexDto);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int insert(InsertReqDto dto) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO product(productName, companyId, price, detail, imgUrl_1, imgUrl_2, imgUrl_3, imgUrl_4, writerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProductName());
			pstmt.setInt(2, dto.getCompanyId());
			pstmt.setLong(3, dto.getPrice());
			pstmt.setString(4, dto.getDetail());
			pstmt.setString(5, dto.getImgUrl_1());
			pstmt.setString(6, dto.getImgUrl_2());
			pstmt.setString(7, dto.getImgUrl_3());
			pstmt.setString(8, dto.getImgUrl_4());
			pstmt.setInt(9, dto.getWriterId());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	public DetailProdRespDto findById(int prodNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT p.id, p.imgUrl_1, p.imgUrl_2, p.imgUrl_3, p.imgUrl_4, c.url, c.name, p.productName, p.price, p.soldCount, p.detail ");
		sb.append("FROM product p INNER JOIN company c ON p.companyId = c.id ");
		sb.append("WHERE p.id = ?");
		String sql = sb.toString();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				DetailProdRespDto dto = DetailProdRespDto.builder()
						.prodId(rs.getInt("p.id"))
						.imgUrl_1(rs.getString("p.imgUrl_1"))
						.imgUrl_2(rs.getString("p.imgUrl_2"))
						.imgUrl_3(rs.getString("p.imgUrl_3"))
						.imgUrl_4(rs.getString("p.imgUrl_4"))
						.url(rs.getString("c.url"))
						.companyName(rs.getString("c.name"))
						.productName(rs.getString("p.productName"))
						.price(rs.getLong("p.price"))
						.soldCount(rs.getInt("p.soldCount"))
						.detail(rs.getString("p.detail"))
						.build();
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
