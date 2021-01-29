package com.shop.shop.domain.favor.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FavorCompListReqDto {
	private int companyId;
	private String companyName;
}
