package com.stockmarket.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.stockmarket.backend.entity.Sector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
	public String company_name;
	public float turnover;
	private String ceo;
	private String board_of_directors;
	private List<String> stock_exchanges = new ArrayList<String>();
	private Sector sector;
	private String write_up;
}
