package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.FreemiLoanQuery;

@Repository
public interface FreemiLoanQueryRepository extends JpaRepository<FreemiLoanQuery, Long> { 

}
