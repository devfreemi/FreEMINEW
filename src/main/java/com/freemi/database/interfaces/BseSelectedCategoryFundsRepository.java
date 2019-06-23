package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFSelectedFunds;

@Service
public interface BseSelectedCategoryFundsRepository extends JpaRepository<BseMFSelectedFunds,String>{
	
	/*@Query("select * from BseMFSelectedFunds f where f.fundCatergory = :fundCategory")
	public List<BseMFSelectedFunds> getFundsByCategory(@Param("fundCategory") String fundCategory);*/
	
	public List<BseMFSelectedFunds> getAllByFundCatergory(String fundCategory);
	
	public BseMFSelectedFunds findOneByRtaCode(String rtaCode);
	public BseMFSelectedFunds findOneByRtaCodeAndIsin(String rtaCode, String isin);
}
