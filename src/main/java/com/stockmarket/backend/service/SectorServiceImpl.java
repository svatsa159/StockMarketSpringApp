package com.stockmarket.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.backend.dao.SectorDao;
import com.stockmarket.backend.dto.SectorDTO;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;

@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	SectorDao sectorDao;
	@Autowired
	ModelMapper mapper;

	@Override

	public Sector getSectorById(long id) throws EntityNotFound {
		return sectorDao.getSectorById(id);
	}

	@Override
	public SectorDTO addSector(SectorDTO sectorDTO) {
		Sector sector = mapper.map(sectorDTO, Sector.class);
		return mapper.map(sectorDao.addSector(sector), SectorDTO.class);
	}

	@Override
	public List<Sector> getAllSectors() {
		// TODO Auto-generated method stub
		return sectorDao.getAllSector();
	}
}
