package com.shop.shop.domain.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.config.DB;
import com.shop.shop.domain.product.dto.CheckoutProdDto;
import com.shop.shop.domain.product.dto.DetailProdRespDto;
import com.shop.shop.domain.product.dto.HeaderBrandDto;
import com.shop.shop.domain.product.dto.IndexDto;
import com.shop.shop.domain.product.dto.InsertReqDto;

public class ProductDao {
	
	public void updateSoldCount(int prodId) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE product SET soldCount = soldCount+1 WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prodId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
	}
	
	public List<CheckoutProdDto> findForBuy(List<Integer> cartList) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CheckoutProdDto> result = new ArrayList<>();
		
		try {
			for (int prodId : cartList) {
				String sql = "SELECT p.id, p.productName, c.name, p.price, p.imgUrl_1 FROM product p INNER JOIN company c ON p.companyId = c.id WHERE p.id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, prodId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					CheckoutProdDto dto = CheckoutProdDto.builder()
							.id(rs.getInt("p.id"))
							.productName(rs.getString("p.productName"))
							.companyName(rs.getString("c.name"))
							.price(rs.getLong("p.price"))
							.imgUrl_1(rs.getString("p.imgUrl_1"))
							.build();
					result.add(dto);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public CheckoutProdDto findForBuy(int id) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.id, p.productName, c.name, p.price, p.imgUrl_1 FROM product p INNER JOIN company c ON p.companyId = c.id WHERE p.id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				CheckoutProdDto result = CheckoutProdDto.builder()
						.id(rs.getInt("p.id"))
						.productName(rs.getString("p.productName"))
						.companyName(rs.getString("c.name"))
						.price(rs.getLong("p.price"))
						.imgUrl_1(rs.getString("p.imgUrl_1"))
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
	
	public List<IndexDto> findAllSortBySoldCount() {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.id, p.productName, c.name, p.price, p.soldCount, p.imgUrl_1 FROM product p INNER JOIN company c ON p.companyId = c.id ORDER BY p.soldCount DESC";
		
		List<IndexDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public List<IndexDto> findAll() {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.id, p.productName, c.name, p.price, p.soldCount, p.imgUrl_1 FROM product p INNER JOIN company c ON p.companyId = c.id ORDER BY p.id DESC";
		
		List<IndexDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public List<IndexDto> findByKeyword(String keyword) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer(); 
		sb.append("SELECT p.id, p.productName, c.name, p.price, ");
		sb.append("p.soldCount, p.imgUrl_1 FROM product p ");
		sb.append("INNER JOIN company c ON p.companyId = c.id ");
		sb.append("WHERE p.productName LIKE '%"+keyword+"%' OR c.name LIKE '%"+keyword+"%'");
		String sql = sb.toString();
		
		List<IndexDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public List<IndexDto> findByCompNo(int compNo) {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer(); 
		sb.append("SELECT p.id, p.productName, c.name, p.price, ");
		sb.append("p.soldCount, p.imgUrl_1 FROM product p ");
		sb.append("INNER JOIN company c ON p.companyId = c.id ");
		sb.append("WHERE p.companyId = ?");
		String sql = sb.toString();
		
		List<IndexDto> result = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, compNo);
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
	
	public List<HeaderBrandDto> getAllCompName() {
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, name FROM company";
		List<HeaderBrandDto> compNameList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HeaderBrandDto dto = HeaderBrandDto.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.build();
				compNameList.add(dto);
			}
			return compNameList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
