package com.stockmarket.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.backend.dto.SectorDTO;
import com.stockmarket.backend.entity.Sector;
import com.stockmarket.backend.service.SectorService;

@RestController
@RequestMapping("/manage_sector")
public class ManageSector {
	@Autowired
	private SectorService sectorService;

	@PostMapping("/add/")
	public ResponseEntity<SectorDTO> addSector(@RequestBody SectorDTO sectorDTO) {
		return new ResponseEntity<SectorDTO>(sectorService.addSector(sectorDTO), HttpStatus.ACCEPTED);

	}

	@GetMapping("/list/")
	public ResponseEntity<List<Sector>> getAllSectors() {
		return new ResponseEntity<List<Sector>>(sectorService.getAllSectors(), HttpStatus.ACCEPTED);
	}

}
