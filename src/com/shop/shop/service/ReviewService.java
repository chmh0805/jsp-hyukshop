package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.review.ReviewDao;
import com.shop.shop.domain.review.dto.DetailReviewRespDto;
import com.shop.shop.domain.review.dto.DetailReviewDto;
import com.shop.shop.domain.review.dto.InsertReviewDto;

public class ReviewService {
	private ReviewDao reviewDao;
	
	public ReviewService() {
		this.reviewDao = new ReviewDao();
	}
	
	public int 글등록(InsertReviewDto dto) {
		return reviewDao.insert(dto);
	}
	
	public int 글갯수(int prodNo) {
		return reviewDao.countByProdId(prodNo);
	}
	
	public List<DetailReviewDto> 글목록(int prodNo) {
		return reviewDao.findByProdNo(prodNo);
	}
	
	public DetailReviewDto 글상세보기(int reviewId) {
		return reviewDao.findById(reviewId);
	}
	
	public List<DetailReviewRespDto> 상세페이지글목록(int prodNo) {
		return reviewDao.findForDetail(prodNo);
	}
	
	public List<DetailReviewRespDto> 글전체목록(int prodNo) {
		return reviewDao.findForAll(prodNo);
	}
	
	public List<DetailReviewRespDto> 내글보기(int userId) {
		return reviewDao.findForMine(userId);
	}
}
