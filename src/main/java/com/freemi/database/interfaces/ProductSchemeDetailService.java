package com.freemi.database.interfaces;
import com.freemi.entity.database.ProductSchemeDetail;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProductSchemeDetailService extends CRUDService<ProductSchemeDetail>{

	public List<ProductSchemeDetail> findBySchemeCode(String schemeCode);
	public List<ProductSchemeDetail> findForRegistryBirthDay();
	public List<ProductSchemeDetail> findForTravel();
	public List<ProductSchemeDetail> findForTaxSaving();
}
