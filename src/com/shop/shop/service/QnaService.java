package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.qna.QnaDao;
import com.shop.shop.domain.qna.dto.AllQnaDto;
import com.shop.shop.domain.qna.dto.DetailQnaDto;
import com.shop.shop.domain.qna.dto.DetailQnaRespDto;
import com.shop.shop.domain.qna.dto.InsertQnaDto;

public class QnaService {
	private QnaDao qnaDao;
	
	public QnaService() {
		this.qnaDao = new QnaDao();
	}
	
	public int 글등록(InsertQnaDto dto) {
		return qnaDao.insert(dto);
	}
	
	public int 글갯수(int prodNo) {
		return qnaDao.countByProdId(prodNo);
	}
	
	public List<DetailQnaDto> 글목록(int prodNo) {
		return qnaDao.findByProdNo(prodNo);
	}
	
	public List<DetailQnaRespDto> 상세페이지글목록(int prodNo) {
		return qnaDao.findForDetail(prodNo);
	}
	
	public DetailQnaDto 글상세보기(int qnaId) {
		return qnaDao.findById(qnaId);
	}
	
	public List<AllQnaDto> 글전체목록(int prodNo) {
		return qnaDao.findForAll(prodNo);
	}
}
