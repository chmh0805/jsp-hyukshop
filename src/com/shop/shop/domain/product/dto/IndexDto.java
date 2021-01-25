package com.shop.shop.domain.product.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IndexDto {
	private int productId;
	private String productName;
	private String companyName;
	private long price;
	private int soldCount;
	private String imgUrl_1;
}
