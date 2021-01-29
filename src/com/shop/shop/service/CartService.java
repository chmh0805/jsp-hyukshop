package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.cart.CartDao;
import com.shop.shop.domain.cart.dto.CartAllDto;
import com.shop.shop.domain.cart.dto.CartDto;

public class CartService {
	private CartDao cartDao;
	
	public CartService() {
		this.cartDao = new CartDao();
	}
	
	public boolean 장바구니여부(int userId, int prodNo) {
		return cartDao.isCart(userId, prodNo);
	}
	
	public int 장바구니등록(CartDto cartDto) {
		return cartDao.addCart(cartDto);
	}
	
	public int 장바구니해제(CartDto cartDto) {
		return cartDao.rmvCart(cartDto);
	}
	
	public List<CartAllDto> 장바구니불러오기(int userId) {
		return cartDao.findByUserId(userId);
	}
	
	public List<Integer> 찜불러오기(int userId) {
		return cartDao.findFavorByUserId(userId);
	}
	
}
