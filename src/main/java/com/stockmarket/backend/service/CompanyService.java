package com.stockmarket.backend.service;

import java.util.List;

import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;

public interface CompanyService {
	public boolean AddCompanywithSector(Company company, long sector_id) throws EntityExists, EntityNotFound;

	public void AddCompanyToStockExchange(long company_id, List<String> stock_exchange_list) throws EntityNotFound;

	Company getCompanyById(long company_id) throws EntityNotFound;
}
