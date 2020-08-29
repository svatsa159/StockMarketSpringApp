package com.stockmarket.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarket.backend.dto.CompanyDTO;
import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.CompanyRepository;
import com.stockmarket.backend.repository.StockExchangeRepository;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

//	@Autowired
//	StockExchangeDaoImpl stockExchangeDaoImpl;

	@Autowired
	StockExchangeRepository stockExchangeRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	@Transactional
	public List<StockExchange> getAllStockExchanges() {
		return (List<StockExchange>) stockExchangeRepository.findAll();

	}

	@Override
	@Transactional
	public boolean addStockExchange(StockExchange exchange) throws EntityExists {

		try {
			stockExchangeRepository.save(exchange);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExists();
		}
		return true;

	}

	@Override
	@Transactional
	public boolean removeStockExchange(String name) throws EntityNotFound {

		StockExchange exchange = stockExchangeRepository.getStockExchangeByName(name);
		if (exchange == null) {
			throw new EntityNotFound("Exchange not found");
		}
		stockExchangeRepository.delete(exchange);

		return true;
	}

	@Override
	@Transactional
	public List<CompanyDTO> getCompaniesByStockExchange(String name) throws EntityNotFound {
		StockExchange exchange = stockExchangeRepository.getStockExchangeByName(name);
		if (exchange == null) {
			throw new EntityNotFound("Exchange not found");
		}
		return exchange.getCompany_id().stream().map(comp_id -> {
			return mapper.map(companyRepository.findById(comp_id).orElse(null), CompanyDTO.class);
		}).collect(Collectors.toList());
	}

}
