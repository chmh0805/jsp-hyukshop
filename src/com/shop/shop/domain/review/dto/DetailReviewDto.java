package com.shop.shop.domain.review.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailReviewDto {
	private int id;
	private String detail;
	private Timestamp createDate;
}
