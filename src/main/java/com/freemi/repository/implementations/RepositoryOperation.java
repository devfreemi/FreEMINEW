package com.freemi.repository.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.CustomerKYSStatus;
import com.freemi.repository.interfaces.KycStatusRepository;

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
