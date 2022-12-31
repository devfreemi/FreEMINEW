package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFSelectedFunds;

@Service
public interface Amcfundmanager {

	public List<BseMFSelectedFunds> getAllSelectedFunds();
	
	public List<BseMFSelectedFunds> getSelectedfunddetails(List<String> fundlist);
}
