package com.shop.shop.domain.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;

public class ProductDao {
	
	public List<Product> findAllWithLimitNum(int limitNum) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, productName, companyName, price, soldCount, imgUrl_1 FROM product WHERE id < ?";
		
		List<Product> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limitNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.productName(rs.getString("productName"))
						.companyName(rs.getString("companyName"))
						.price(rs.getLong("price"))
						.soldCount(rs.getInt("soldCount"))
						.imgUrl_1(rs.getString("imgUrl_1"))
						.build();
				result.add(product);
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
