package com.stockmarket.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.backend.dto.AddSectorWrapper;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.service.SectorService;

@RestController
@RequestMapping("/api/manage_sector")
public class ManageSector {
	@Autowired
	private SectorService sectorService;

	@PostMapping("/add/")
	public ResponseEntity<AddSectorWrapper> addSector(@RequestBody AddSectorWrapper AddSectorWrapper) {
		return new ResponseEntity<AddSectorWrapper>(sectorService.addSector(AddSectorWrapper), HttpStatus.ACCEPTED);

	}

	@GetMapping("/list/")
	public ResponseEntity<List<Sector>> getAllSectors() {
		return new ResponseEntity<List<Sector>>(sectorService.getAllSectors(), HttpStatus.OK);
	}

	@GetMapping("/list_companies_by_sector/{sector_id}")
	public ResponseEntity<Object> getCompanyBySector(@PathVariable("sector_id") long id) {
		try {
			return new ResponseEntity<>(sectorService.getCompanyOfSector(id), HttpStatus.OK);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
