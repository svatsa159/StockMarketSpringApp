package com.stockmarket.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.CompanyRepository;

@Service
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public boolean AddCompany(Company company) throws EntityExists {
		try {
			companyRepository.save(company);
			return true;
		} catch (DataIntegrityViolationException e) {
			throw new EntityExists();
		}
	}

	@Override
	public Company getCompanyById(long company_id) throws EntityNotFound {
		Company company = (Company) companyRepository.findById(company_id).orElse(null);
		if (company == null) {
			throw new EntityNotFound();
		} else {
			return company;
		}

	}

	@Override
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}

}
