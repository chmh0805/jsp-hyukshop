package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.favor.FavorDao;
import com.shop.shop.domain.favor.dto.FavorCompListReqDto;
import com.shop.shop.domain.favor.dto.FavorDto;
import com.shop.shop.domain.favor.dto.FavorReqDto;

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
	
	public List<FavorReqDto> 찜불러오기(int userId) {
		return favorDao.findByUserId(userId);
	}
	
	public List<FavorReqDto> 찜불러오기(int userId, int compId) {
		return favorDao.sortByCompId(userId, compId);
	}
	
	public List<FavorCompListReqDto> 찜한회사목록(int userId) {
		return favorDao.findCompListByUserId(userId);
	}
}
