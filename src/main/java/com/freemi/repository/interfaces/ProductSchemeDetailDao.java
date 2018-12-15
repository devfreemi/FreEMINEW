package com.freemi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.freemi.entity.database.ProductSchemeDetail;

@Component
public interface ProductSchemeDetailDao extends JpaRepository<ProductSchemeDetail, Long>{
	public List<ProductSchemeDetail> findBySchemeCode(String schemeCode);
	
//	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.isDirect ='N' AND scheme.isLiquid ='Y' Order by scheme.nav ASC ")
	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.category in ('Debt') and scheme.sub_Category in ('Low Duration','Medium Duration','Ultra Short Duration') and scheme.isDirect ='N' and scheme.schemeOption='Z'  Order by scheme.schemeName ASC")
	public List<ProductSchemeDetail> findForRegistryBirthDay();
	
//	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.isDirect ='N' AND scheme.isLiquid ='Y' Order by scheme.nav DESC ")
	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.category in ('Debt') and scheme.sub_Category in ('Low Duration','Medium Duration','Ultra Short Duration') and scheme.isDirect ='N' and scheme.schemeOption='Z'  Order by scheme.schemeName ASC")
	public List<ProductSchemeDetail> findForTravel();
	
//	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.isDirect ='N' AND scheme.sub_Category ='ELSS' Order by scheme.nav ASC ")
	@Query("Select scheme From ProductSchemeDetail as scheme where scheme.category in ('Debt') and scheme.sub_Category in ('Low Duration','Medium Duration','Ultra Short Duration') and scheme.isDirect ='N' and scheme.schemeOption='Z'  Order by scheme.schemeName ASC")
	public List<ProductSchemeDetail> findForTaxSaving();
}
