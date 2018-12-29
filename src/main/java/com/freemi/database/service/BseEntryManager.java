package com.freemi.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface BseEntryManager {
	
	public boolean saveCustomerDetails(BseMFInvestForm customerForm);
	public boolean savetransactionDetails(SelectMFFund selectedMFFund);
	public List<SelectMFFund> getMFOrderHistory(String customerId);
	public List<BseMFInvestForm> getCustomerDetails(String customerId);

}
