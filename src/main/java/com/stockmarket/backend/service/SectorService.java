package com.stockmarket.backend.service;

import java.util.List;

import com.stockmarket.backend.dto.SectorDTO;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;

public interface SectorService {
	public Sector getSectorById(long id) throws EntityNotFound;

	public SectorDTO addSector(SectorDTO sectorDTO);

	public List<Sector> getAllSectors();
}
