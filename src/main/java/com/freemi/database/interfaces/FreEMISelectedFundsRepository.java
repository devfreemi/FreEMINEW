package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFSelectedFunds;
import com.freemi.entity.investment.FreemiSelectedFunds;

@Service
public interface FreEMISelectedFundsRepository extends JpaRepository<FreemiSelectedFunds,String>{
	
	public List<FreemiSelectedFunds> findAllByAmcenabledAndFundactivefortransactionAndTopfundsview(String amcenabled,String selectedfundactive,String explorerfundsactive);
	
	public List<FreemiSelectedFunds> findByAmcenabledAndFundactivefortransactionAndFreemifundcodeIn(String amcenabled,String selectedfundactive,List<String> fundlist);
	
	/*
	 * public List<BseMFSelectedFunds> getAllByFundCatergory(String fundCategory);
	 * 
	 * public BseMFSelectedFunds findOneByRtaCode(String rtaCode); public
	 * BseMFSelectedFunds findOneByRtaCodeAndIsin(String rtaCode, String isin);
	 */
}
