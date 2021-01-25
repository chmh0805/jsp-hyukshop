package com.shop.shop.domain.product;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private int id;
	private String productName;
	private int companyId;
	private long price;
	private int soldCount;
	private String detail;
	private String imgUrl_1;
	private String imgUrl_2;
	private String imgUrl_3;
	private String imgUrl_4;
	private Timestamp createDate;
	private Timestamp updateDate;
}
