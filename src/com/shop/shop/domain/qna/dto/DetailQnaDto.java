package com.shop.shop.domain.qna.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailQnaDto {
	private int id;
	private String detail;
	private Timestamp createDate;
}
