package com.stockmarket.backend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.backend.dto.AddCompanyToStockExchange;
import com.stockmarket.backend.dto.AddCompanyWrapper;
import com.stockmarket.backend.dto.CompanyDTO;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.service.CompanyService;

@RestController
@RequestMapping("/manage_company")
public class ManageCompany {
	@Autowired
	CompanyService companyService;
	@Autowired
	ModelMapper mapper;

	@PostMapping("/add/")
	public ResponseEntity<Object> addCompany(@RequestBody AddCompanyWrapper manageCompanyRequestWrapper) {

		try {
			return new ResponseEntity<>(
					mapper.map(companyService.AddCompanywithSector(manageCompanyRequestWrapper.company,
							manageCompanyRequestWrapper.sector_id), CompanyDTO.class),
					HttpStatus.ACCEPTED);
		} catch (EntityExists e) {
			return new ResponseEntity<>("Company Exists", HttpStatus.BAD_REQUEST);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/add_to_stock_exchange")
	public ResponseEntity<Object> addCompanyToStockExchange(
			@RequestBody AddCompanyToStockExchange addCompanyToStockExchange) {
		try {
			companyService.AddCompanyToStockExchange(addCompanyToStockExchange.company_id,
					addCompanyToStockExchange.stock_exchange_list);
			return ResponseEntity.ok(null);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/get_by_id/{id}")
	public ResponseEntity<Object> getCompanyById(@PathVariable("id") long id) {
		try {

			return ResponseEntity.ok(companyService.getCompanyById(id));
		} catch (EntityNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/get_by_pattern")
	public ResponseEntity<Object> getCompaniesByPattern(@RequestParam String pattern) {
		return new ResponseEntity<>(companyService.getCompaniesByPattern(pattern), HttpStatus.OK);
	}

}
