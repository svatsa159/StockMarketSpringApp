package com.stockmarket.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarket.backend.dto.AddSectorWrapper;
import com.stockmarket.backend.dto.CompanyDTO;
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
			throw new EntityNotFound("Sector not found");
		}
		return sector;
	}

	@Override
	@Transactional
	public AddSectorWrapper addSector(AddSectorWrapper AddSectorWrapper) {
		Sector sector = mapper.map(AddSectorWrapper, Sector.class);
		return mapper.map(sectorRepository.save(sector), AddSectorWrapper.class);
	}

	@Override
	@Transactional
	public List<Sector> getAllSectors() {
		// TODO Auto-generated method stub
		return (List<Sector>) sectorRepository.findAll();
	}

	@Override
	@Transactional
	public List<CompanyDTO> getCompanyOfSector(long sectorId) throws EntityNotFound {
		// TODO Auto-generated method stub
		Sector sector = sectorRepository.findById(sectorId).orElse(null);
		if (sector == null) {
			throw new EntityNotFound("Sector not found");
		}
		return sector.getCompany_list().stream().map(company -> mapper.map(company, CompanyDTO.class))
				.collect(Collectors.toList());
	}

}
