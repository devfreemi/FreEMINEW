package com.freemi.database.implementations;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.service.BseEntryManager;
import com.freemi.entity.database.UserBankDetails;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.repository.interfaces.BseCustomerCrudRespository;
import com.freemi.repository.interfaces.BseTransCrudRepository;

@Service
public class BseEntryServiceImpl implements BseEntryManager {

	@Autowired
	BseCustomerCrudRespository bseCustomerCrudRespository;

	@Autowired
	BseTransCrudRepository bseTransCrudRepository;

	private static final Logger logger = LogManager.getLogger(BseEntryServiceImpl.class);

	@Override
	public boolean saveCustomerDetails(BseMFInvestForm customerForm) {
		// TODO Auto-generated method stub
		boolean flag = true;

		//Generate client ID

		customerForm.setClientID(customerForm.getInvName().substring(0, 3)+customerForm.getPan1().substring(6));
		System.out.println("Customer ID- "+ customerForm.getClientID());
		customerForm.getBankDetails().setClientID(customerForm.getInvName().substring(0, 3)+customerForm.getPan1().substring(6));
		logger.info("Transaction started to save BSE customer registrastion data");
		bseCustomerCrudRespository.save(customerForm);
		bseCustomerCrudRespository.flush();
		return flag;
	}

	@Override
	public boolean savetransactionDetails(SelectMFFund selectedMFFund) {
		// TODO Auto-generated method stub
		boolean flag = true;

		//Generate client ID
		selectedMFFund.setClientID("DEBASISH87");
		logger.info("Transaction started to save BSE transaction data");
		bseTransCrudRepository.saveAndFlush(selectedMFFund);
		return flag;
	}

	@Override
	public List<SelectMFFund> getMFOrderHistory(String customerId) {
		return bseTransCrudRepository.getByClientID(customerId);
	}

	@Override
	public List<BseMFInvestForm> getCustomerDetails(String customerId) {
		// TODO Auto-generated method stub
		return bseCustomerCrudRespository.getByClientID(customerId);
	}


}
