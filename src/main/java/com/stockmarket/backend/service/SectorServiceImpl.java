package com.stockmarket.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarket.backend.dto.SectorDTO;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	SectorRepository sectorRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	@Transactional
	public Sector getSectorById(long id) throws EntityNotFound {
		Sector sector = sectorRepository.findById(id).orElse(null);
		if (sector == null) {
			throw new EntityNotFound();
		}
		return sector;
	}

	@Override
	@Transactional
	public SectorDTO addSector(SectorDTO sectorDTO) {
		Sector sector = mapper.map(sectorDTO, Sector.class);
		return mapper.map(sectorRepository.save(sector), SectorDTO.class);
	}

	@Override
	@Transactional
	public List<Sector> getAllSectors() {
		// TODO Auto-generated method stub
		return (List<Sector>) sectorRepository.findAll();
	}
}
