package com.stockmarket.backend.dao;

import java.util.List;

import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;

public interface SectorDao {
	public Sector getSectorById(long id) throws EntityNotFound;

	public Sector addSector(Sector sector);

	public List<Sector> getAllSector();
}
