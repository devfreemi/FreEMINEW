package com.freemi.database.interfaces;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.BseDailyTransCounter;

@Service
public interface BseTransCountCrudRepository extends JpaRepository<BseDailyTransCounter, Long>{
	
	@Transactional
	@Modifying
	@Query("update BseDailyTransCounter b set b.dayCounter=b.dayCounter+1 where b.date= :currentdate")
	public int increaseCounterByOne(@Param("currentdate") Date date);
	
	@Query("select dayCounter from BseDailyTransCounter a where a.date = :selectedDate")
	public String findOneByDate(@Param("selectedDate") Date date);

}
