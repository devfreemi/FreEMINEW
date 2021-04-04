package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.MfAllInvestorValueByCategory;

@Repository
public interface BseCustomerMfRepository extends JpaRepository<MfAllInvestorValueByCategory,Integer>{
	
	public List<MfAllInvestorValueByCategory> getAllByPan(String pan);
	
	public MfAllInvestorValueByCategory getOneByChannelProductCodeAndFolioNumber(String productCode, String folioNumber);
	
	@Query(value = "select distinct(a.FOLIO_NO) from investors_balance_view_all a, bsemf_schemes_master m where m.AMC_Code=a.AMC_SHORT and a.BSEMF_SCHEME_CODE= :bseschemeCode and a.PAN= :pan",nativeQuery = true)
	public List<String> getCustomerFoliosForPurchase(@Param("pan") String pan,@Param("bseschemeCode") String bseSchemeCode);
	
	public MfAllInvestorValueByCategory findOneByPanAndChannelProductCodeAndRtaAgentAndFolioNumber(String pan, String channelPartnerCode, String rtaAgent, String folioNumber);
	
//	@Query(value = "select SUM(inv.INVESTMENT_AMOUNT), SUM(inv.MARKET_VALUE) FROM investors_balance_view_all inv WHERE inv.PAN= :pan",nativeQuery = true)
//	public String getCustomerMFInvestmentAmount(@Param("pan") String pan);
	
//	@Cacheable(value = "mfbalancedata", unless = "#result == null", key= "#mobile")
	@Query(value = "select inv.PAN, SUM(inv.INVESTMENT_AMOUNT), SUM(inv.MARKET_VALUE) FROM investors_balance_view_all inv,bsemf_customers customers WHERE customers.PAN_NO_1=inv.PAN AND customers.MOBILE_NO= :mobile AND customers.ACCOUNT_ACTIVE='Y'",nativeQuery = true)
	public String getCustomerMFInvestmentAmount(@Param("mobile") String mobile);
}
