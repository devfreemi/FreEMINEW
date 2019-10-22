package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.investment.FolioCreationStatus;
import com.freemi.entity.investment.PanValidationStatus;


@Service
public interface DatabaseEntryManager {	
	public boolean savekycstatus(PanValidationStatus kycStatusData);
	
	public boolean saveCampaignEntry(CampaignSignupForm formData);
	
	public List<PanValidationStatus> getkycfromsystem(String panNumber);
	
	public boolean saveRegistryPurchaseRecords(FolioCreationStatus statusData);

}
