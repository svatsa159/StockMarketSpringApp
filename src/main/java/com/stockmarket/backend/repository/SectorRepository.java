package com.stockmarket.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stockmarket.backend.entity.Sector;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Long> {

}
