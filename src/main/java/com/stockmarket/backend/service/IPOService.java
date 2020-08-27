package com.stockmarket.backend.service;

import java.util.List;

import com.stockmarket.backend.dto.AddIPO_Wrapper;
import com.stockmarket.backend.dto.IPO_DTO;
import com.stockmarket.backend.exception.DateFormatError;
import com.stockmarket.backend.exception.EntityNotFound;

public interface IPOService {
	public AddIPO_Wrapper addIPO(AddIPO_Wrapper ipoDTO) throws EntityNotFound, DateFormatError;

	List<IPO_DTO> getIPO_OfCompany(Long company_id) throws EntityNotFound;
}
