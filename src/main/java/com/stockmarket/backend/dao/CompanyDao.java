package com.stockmarket.backend.dao;

import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;

public interface CompanyDao {
	public boolean AddCompany(Company company) throws EntityExists;

	Company getCompanyById(long company_id) throws EntityNotFound;

	Company saveCompany(Company company);
	// TODO Deactivate Company
//	public boolean deactivateCompany(Company company) throws EntityNotFound;

	// TODO Update IPO Related Data
//	public boolean updateIPO() throws EntityNotFound;
}
