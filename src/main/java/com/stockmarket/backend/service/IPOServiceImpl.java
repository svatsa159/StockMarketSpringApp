package com.stockmarket.backend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.backend.dto.AddIPO_Wrapper;
import com.stockmarket.backend.dto.IPO_DTO;
import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.entity.IPO;
import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.DateFormatError;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.CompanyRepository;
import com.stockmarket.backend.repository.IPORepository;
import com.stockmarket.backend.repository.StockExchangeRepository;

@Service
public class IPOServiceImpl implements IPOService {

	@Autowired
	IPORepository ipoRepository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	StockExchangeRepository stockExchangeRepository;
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public AddIPO_Wrapper addIPO(AddIPO_Wrapper ipoWrapper) throws EntityNotFound, DateFormatError {
		IPO ipoData = mapper.map(ipoWrapper, IPO.class);
		Date date;
		StockExchange exchange = stockExchangeRepository.getStockExchangeByName(ipoWrapper.getStockExchangeName());
		if (exchange == null) {
			throw new EntityNotFound("Exchange not found");
		}
		Company company = companyRepository.findById(ipoWrapper.getCompany_id()).orElse(null);
		if (company == null) {
			throw new EntityNotFound("Company not found");
		}
		try {
			date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(ipoWrapper.getDate_string());

		} catch (ParseException e) {
			throw new DateFormatError();
		}
		ipoData.setCompany(company);
		ipoData.setStockExchange(exchange);
		ipoData.setOpen_date_time(date);
		ipoRepository.save(ipoData);
		return ipoWrapper;
	}

	@Override
	public List<IPO_DTO> getIPO_OfCompany(Long company_id) throws EntityNotFound {
		Company company = companyRepository.findById(company_id).orElse(null);
		if (company == null) {
			throw new EntityNotFound("Company not found");
		}
		return ipoRepository.getIPOByCompanyId(company_id).stream().map(ipo -> mapper.map(ipo, IPO_DTO.class))
				.collect(Collectors.toList());
	}

}
