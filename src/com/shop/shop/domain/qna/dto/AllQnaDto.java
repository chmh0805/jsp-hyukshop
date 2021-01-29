package com.shop.shop.domain.qna.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AllQnaDto {
	private int id;
	private String detail;
	private int optionNo;
	private String name;
	private String password;
	private Timestamp createDate;
}
