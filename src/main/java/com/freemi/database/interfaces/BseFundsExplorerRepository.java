package com.freemi.database.interfaces;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.freemi.entity.investment.BseFundsScheme;

public interface BseFundsExplorerRepository extends PagingAndSortingRepository<BseFundsScheme, Integer>{
	
	public Page<BseFundsScheme> findAll(Pageable page);
	
	public List<BseFundsScheme> findAllBySchemeCode(String schemeCode, Pageable page);

}
