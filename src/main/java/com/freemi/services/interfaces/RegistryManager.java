package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.general.MethodRequestResponse;
import com.freemi.entity.investment.RegistryFunds;
import com.freemi.entity.investment.RegistryWish;
import com.freemi.entity.investment.SelectMFFund;
import com.freemi.entity.investment.TransactionStatus;

@Service
public interface RegistryManager {

    public RegistryFunds serachregistryfund(String schemecode);
    public List<RegistryFunds> getRegistryfunds(String criteria, String obj2);
    
    public MethodRequestResponse saveRegistryplannerrequest(RegistryWish registrywish);
    
    public RegistryWish getregistryDetails(String registryrequestid);
    
    public TransactionStatus purchaseregistrysip(SelectMFFund selectedFund);
    
}
