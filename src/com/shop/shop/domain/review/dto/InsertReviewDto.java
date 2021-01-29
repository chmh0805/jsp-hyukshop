package com.shop.shop.domain.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertReviewDto {
	private int userId;
	private int productId;
	private String detail;
}
