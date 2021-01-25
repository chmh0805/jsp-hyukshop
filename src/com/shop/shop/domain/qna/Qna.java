package com.shop.shop.domain.qna;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Qna {
	private int id;
	private int userId;
	private int productId;
	private int optionNo; // 1:배송관련 2:상품관련 3:기타
	private String password;
	private String detail;
	private Timestamp createDate;
}
