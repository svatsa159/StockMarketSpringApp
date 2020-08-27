package com.stockmarket.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarket.backend.dto.CompanyDTO;
import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.CompanyRepository;
import com.stockmarket.backend.repository.StockExchangeRepository;

@Service
@Transactional(rollbackFor = EntityNotFound.class)
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	SectorService sectorService;
	@Autowired
	StockExchangeRepository stockExchangeRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	@Transactional
	public boolean AddCompanywithSector(Company company, long sector_id) throws EntityExists, EntityNotFound {
//		companyDaoImpl.AddCompany(company);
		Sector sector = sectorService.getSectorById(sector_id);
		company.setSector(sector);
		try {
			companyRepository.save(company);
			return true;
		} catch (

		DataIntegrityViolationException e) {
			throw new EntityExists();
		}

	}

	@Override
	@Transactional
	public void AddCompanyToStockExchange(long company_id, List<String> stock_exchange_list) throws EntityNotFound {
		Company company = (Company) companyRepository.findById(company_id).orElse(null);
		if (company == null) {
			throw new EntityNotFound();
		}
		List<String> exchangeList = company.getStock_exchanges();
		for (String ID : stock_exchange_list) {
			StockExchange exchange = stockExchangeRepository.getStockExchangeByName(ID);
			if (exchange == null) {
				throw new EntityNotFound();
			}
			exchangeList.add(exchange.getName());
			List<Long> company_ids = exchange.getCompany_id();
			company_ids.add(company_id);
			exchange.setCompany_id(company_ids);
			stockExchangeRepository.save(exchange);

		}
		company.setStock_exchanges(exchangeList);
		System.out.println(company);
		companyRepository.save(company);
	}

	@Override
	@Transactional
	public CompanyDTO getCompanyById(long company_id) throws EntityNotFound {
		Company company = (Company) companyRepository.findById(company_id).orElse(null);
		if (company == null) {
			throw new EntityNotFound();
		} else {
			return mapper.map(company, CompanyDTO.class);
		}

	}

	@Override
	public List<String> getCompaniesByPattern(String pattern) {

		return companyRepository.findUsersWithPartOfName(pattern);
	}

}
