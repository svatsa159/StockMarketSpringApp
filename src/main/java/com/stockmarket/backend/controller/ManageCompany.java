package com.stockmarket.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.backend.dto.AddCompanyRequestWrapper;
import com.stockmarket.backend.dto.AddCompanyToStockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.service.CompanyServiceImpl;

@RestController
@RequestMapping("/manage_company")
public class ManageCompany {
	@Autowired
	CompanyServiceImpl companyServiceImpl;

	@PostMapping("/add/")
	public ResponseEntity<Object> addCompany(@RequestBody AddCompanyRequestWrapper manageCompanyRequestWrapper) {

		try {
			companyServiceImpl.AddCompanywithSector(manageCompanyRequestWrapper.company,
					manageCompanyRequestWrapper.sector_id);
		} catch (EntityExists e) {
			return new ResponseEntity<>("Entity Exists", HttpStatus.BAD_REQUEST);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>("Entity Not Found", HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(null);
	}

	@PostMapping("/add_to_stock_exchange")
	public ResponseEntity<Object> addCompanyToStockExchange(
			@RequestBody AddCompanyToStockExchange addCompanyToStockExchange) {
		try {
			companyServiceImpl.AddCompanyToStockExchange(addCompanyToStockExchange.company_id,
					addCompanyToStockExchange.stock_exchange_list);
			return ResponseEntity.ok(null);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>("Entity Not Found", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/get_by_id/{id}")
	public ResponseEntity<Object> getCompanyById(@PathVariable("id") long id) {
		try {

			return ResponseEntity.ok(companyServiceImpl.getCompanyById(id));
		} catch (EntityNotFound e) {
			return new ResponseEntity<>("Entity Not Found", HttpStatus.BAD_REQUEST);
		}
	}

}
