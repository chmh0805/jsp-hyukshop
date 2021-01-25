package com.shop.shop.service;

import com.shop.shop.domain.favor.FavorDao;
import com.shop.shop.domain.product.dto.FavorDto;

public class FavorService {
	private FavorDao favorDao;
	
	public FavorService() {
		this.favorDao = new FavorDao();
	}
	
	public boolean 찜여부(int userId, int prodNo) {
		return favorDao.isFavor(userId, prodNo);
	}
	
	public int 찜등록(FavorDto favorDto) {
		return favorDao.addFavor(favorDto);
	}
	
	public int 찜해제(FavorDto favorDto) {
		return favorDao.rmvFavor(favorDto);
	}
}
