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

import com.stockmarket.backend.dto.AddIPO_Wrapper;
import com.stockmarket.backend.exception.DateFormatError;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.service.IPOService;

@RestController
@RequestMapping("/manage_ipo")
public class ManageIPO {

	@Autowired
	IPOService ipoService;

	@PostMapping("/add")
	public ResponseEntity<Object> addIPO(@RequestBody AddIPO_Wrapper ipoWrapper) {
		try {
			return new ResponseEntity<>(ipoService.addIPO(ipoWrapper), HttpStatus.ACCEPTED);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);
		} catch (DateFormatError e) {
			return new ResponseEntity<>("Date Format Incorrect", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/get_by_company_id/{id}")
	public ResponseEntity<Object> getIPO_OfCompany(@PathVariable("id") long company_id) {
		try {
			return new ResponseEntity<>(ipoService.getIPO_OfCompany(company_id), HttpStatus.ACCEPTED);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>("Entity not found", HttpStatus.BAD_REQUEST);
		}

	}

}
