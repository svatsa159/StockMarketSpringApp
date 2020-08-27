package com.stockmarket.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIPO_Wrapper {
	private Long company_id;
	private String stockExchangeName;
	private float price_per_share;
	private long number_of_shares;
	private String date_string;
	private String remarks;
}
