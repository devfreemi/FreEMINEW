package com.freemi.database.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.KycStatusRepository;
import com.freemi.entity.database.CustomerKYSStatus;

@Service
@Transactional
public class RepositoryOperation  {

	@Autowired
	KycStatusRepository kycStatusRepository;
	
	public boolean savekycstatus(CustomerKYSStatus kycStatusData){
		
		boolean flag = true;
		/*try{
			kycStatusRepository.save(kycStatusData);
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}*/
		
		return flag;
	}
	
	
}
