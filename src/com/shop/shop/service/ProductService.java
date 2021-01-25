package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.product.ProductDao;
import com.shop.shop.domain.product.dto.DetailProdRespDto;
import com.shop.shop.domain.product.dto.IndexDto;
import com.shop.shop.domain.product.dto.InsertReqDto;

public class ProductService {
	private ProductDao productDao;
	
	public ProductService() {
		productDao = new ProductDao();
	}
	
	public List<IndexDto> 상품전체보기(int limitNum) {
		return productDao.findAllWithLimitNum(limitNum);
	}
	
	public int 상품등록(InsertReqDto dto) {
		return productDao.insert(dto);
	}
	
	public DetailProdRespDto 상품상세보기(int prodNo) {
		return productDao.findById(prodNo);
	}
	
}
