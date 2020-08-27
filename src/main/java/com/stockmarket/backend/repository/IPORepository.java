package com.stockmarket.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stockmarket.backend.entity.IPO;

public interface IPORepository extends CrudRepository<IPO, Long> {
	public List<IPO> getIPOByCompanyId(long id);

}
