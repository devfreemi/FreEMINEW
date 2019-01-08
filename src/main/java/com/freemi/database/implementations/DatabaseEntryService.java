package com.freemi.database.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.CampaignSignupRepository;
import com.freemi.database.interfaces.KycStatusRepository;
import com.freemi.database.interfaces.RegistryPurchaseRecords;
import com.freemi.database.service.DatabaseEntryManager;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.PanValidationStatus;

@Service
public class DatabaseEntryService implements DatabaseEntryManager{
	
	private static final Logger logger = LogManager.getLogger(DatabaseEntryService.class);

	@Autowired
	KycStatusRepository kycStatusRepository;
	//KycStatusRepository kycStatusRepository = BeanUtil.getBean(KycStatusRepository.class);
	
	@Autowired
	CampaignSignupRepository campaignSignupRepository;
	
	@Autowired
	RegistryPurchaseRecords registryPurchaseRecords;
	
	@Override
	@Transactional
	public boolean savekycstatus(PanValidationStatus kycStatusData) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try{
			kycStatusRepository.save(kycStatusData);
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		
		return flag;
	}


	@Override
	@Transactional
	public boolean saveCampaignEntry(CampaignSignupForm formData) {
		boolean flag = true;
		try{
			campaignSignupRepository.save(formData);
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		
		return flag;
	}


	@Override
	public List<PanValidationStatus> getkycfromsystem(String panNumber) {
		
		try{
			return kycStatusRepository.findByPanNumber(panNumber.toUpperCase());
			
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
		
	}


	@Override
	public boolean saveRegistryPurchaseRecords(FolioCreationStatus statusData) {
		boolean flag = true;
		try{
			registryPurchaseRecords.save(statusData);
			logger.info("Saved transaction details successully- "+ statusData.getFolioNumber());
		}catch(Exception e){
			logger.error("Error saving data-"+ e.getMessage());
			flag=false;
		}
		
		return flag;
	}
		

}
