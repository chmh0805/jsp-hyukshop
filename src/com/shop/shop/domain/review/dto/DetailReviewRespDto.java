package com.shop.shop.domain.review.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailReviewRespDto {
	private int id;
	private String name;
	private String detail;
	private Timestamp createDate;
}
