package com.stockmarket.backend.dto;

import com.stockmarket.backend.entity.Company;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCompanyWrapper {
	public Company company;
	public long sector_id;
}
