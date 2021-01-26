package com.shop.shop.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.shop.shop.domain.product.dto.HeaderBrandDto;
import com.shop.shop.service.ProductService;

public class HeaderBrandFilter implements Filter {
	private ProductService productService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		productService = new ProductService();
		
		List<HeaderBrandDto> brandNameList = productService.회사명리스트(); 
		request.setAttribute("brandNameList", brandNameList);
		
		chain.doFilter(request, response);
	}
}
