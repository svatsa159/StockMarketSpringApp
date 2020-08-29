package com.stockmarket.backend.service;

import java.util.List;

import com.stockmarket.backend.dto.AddSectorWrapper;
import com.stockmarket.backend.dto.CompanyDTO;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;

public interface SectorService {
	public Sector getSectorById(long id) throws EntityNotFound;

	public AddSectorWrapper addSector(AddSectorWrapper sectorDTO);

	public List<Sector> getAllSectors();

	List<CompanyDTO> getCompanyOfSector(long sectorId) throws EntityNotFound;
}
