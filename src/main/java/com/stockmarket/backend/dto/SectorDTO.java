package com.stockmarket.backend.dto;

import java.util.List;

import com.stockmarket.backend.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {
	private long id;
	private String name;
	private String brief;
	private List<Company> company_list;
}
