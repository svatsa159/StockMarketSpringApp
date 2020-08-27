package com.stockmarket.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stockmarket.backend.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
	@Query("SELECT u.company_name FROM Company u WHERE lower(u.company_name) LIKE lower(CONCAT('%',:name,'%'))")
	List<String> findUsersWithPartOfName(@Param("name") String company_name);
}
