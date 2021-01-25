package com.shop.shop.domain.qna.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertQnaDto {
	private int userId;
	private int productId;
	private int optionNo;
	private String password;
	private String detail;
}
