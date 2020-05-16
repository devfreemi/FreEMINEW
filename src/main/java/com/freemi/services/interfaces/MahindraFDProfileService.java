package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.database.respository.mahindra.MahindraKycdocupdateform;
import com.freemi.entity.investment.mahindra.MahindraFDListItem;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;

@Service
public interface MahindraFDProfileService {

    public List<Mahindrapurchasehistory> getPurchaseHistory(String mobile,String pan);
    
    public List<MahindraFDListItem> getMahidraFdList(String mobile,String pan);
    
    public MahindraResponse verifyPaymentStatus(String customerId, String mobile, String applicationNo,String emailid);
    
    public MahindraResponse getlatestkycdocuments(String mobile);
    
    public MahindraResponse updatecustomerkycdocuments(MahindraKycdocupdateform docform);
}
