package com.shop.shop.domain.review;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Review {
	private int id;
	private int userId;
	private int productId;
	private String detail;
	private Timestamp createDate;
	private Timestamp updateDate;
}
