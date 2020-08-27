package com.stockmarket.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.SectorRepository;

@Service
public class SectorDaoImpl implements SectorDao {

	@Autowired
	SectorRepository sectorRepository;

	@Override

	public Sector getSectorById(long id) throws EntityNotFound {
		Optional<Sector> sector = sectorRepository.findById(id);
		Sector returnSector = sector.orElse(null);
		if (returnSector == null) {
			throw new EntityNotFound();
		}
		return returnSector;
	}

	@Override
	public Sector addSector(Sector sector) {
		return sectorRepository.save(sector);
	}

	@Override
	public List<Sector> getAllSector() {
		return (List<Sector>) sectorRepository.findAll();
	}
}
