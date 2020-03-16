package com.freemi.database.respository.mahindra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.mahindra.MahindraOtherCountryTaxDetails;

public interface MMCustOtherCountryTaxDetailsRepository extends JpaRepository<MahindraOtherCountryTaxDetails, Integer> {
	
	public boolean existsByCustomeridAndActive(String customerId,String active);
	
	public boolean existsByTaxid(String taxuniqueid);
	public List<MahindraOtherCountryTaxDetails> findAllByCustomeridAndActive(String customerId, String active);
	public List<MahindraOtherCountryTaxDetails> findAllByCustomerid(String customerId);
	
	public MahindraOtherCountryTaxDetails findOneByCustomeridAndTaxCountryAndTaxidentificationtypeAndTaxidentificationnoAndActive(String customerid, String taxcountry, String identificaionType,String identificationNo, String active);
}
