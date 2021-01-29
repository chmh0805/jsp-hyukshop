package com.shop.shop.domain.favor.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavorReqDto {
	private int id;
	private int productId;
	private String productName;
	private String companyName;
	private long price;
	private int soldCount;
	private String imgUrl_1;
}