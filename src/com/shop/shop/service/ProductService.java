package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.product.Product;
import com.shop.shop.domain.product.ProductDao;

public class ProductService {
	private ProductDao productDao;
	
	public ProductService() {
		productDao = new ProductDao();
	}
	
	public List<Product> 상품전체보기(int limitNum) {
		return productDao.findAllWithLimitNum(limitNum);
	}
}
