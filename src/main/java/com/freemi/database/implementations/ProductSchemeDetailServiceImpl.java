package com.freemi.database.implementations;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.ProductSchemeDetailDao;
import com.freemi.database.interfaces.ProductSchemeDetailService;
import com.freemi.entity.database.ProductSchemeDetail;

@Service
public class ProductSchemeDetailServiceImpl implements ProductSchemeDetailService {

     @Autowired
	ProductSchemeDetailDao productSchemeDetailDao;
     @PersistenceContext
     private EntityManager em;
     @Transactional
	public ProductSchemeDetail save(ProductSchemeDetail entity) {
    	 if (entity.getProductScId() == null) {
    	        em.persist(entity);
    	        return entity;
    	    } else {
    	        return em.merge(entity);
    	    }
		 //return productSchemeDetailDao.save(entity);
	}

	@Override
	@Transactional
	public ProductSchemeDetail getById(Serializable id) {
		// TODO Auto-generated method stub
		//return entityManager.find(ProductSchemeDetail.class, id);
		return productSchemeDetailDao.getOne((Long) id);
	}

	@Override
	public List<ProductSchemeDetail> getAll() {
		// TODO Auto-generated method stub
		//Query query = entityManager.createNamedQuery(
				//"ProductSchemeDetail.findAll", ProductSchemeDetail.class);
		//return query.getResultList();
		return productSchemeDetailDao.findAll();
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//productSchemeDetailDao.delete((Long) id);
	}

	@Override
	public List<ProductSchemeDetail> findBySchemeCode(String schemeCode) {
		// TODO Auto-generated method stub
		System.out.println("@@@@ schemeCode ="+schemeCode);
		return productSchemeDetailDao.findBySchemeCode(schemeCode);
	}

	@Override
	public List<ProductSchemeDetail> findForRegistryBirthDay() {
		// TODO Auto-generated method stub
		return productSchemeDetailDao.findForRegistryBirthDay();
	}

	@Override
	public List<ProductSchemeDetail> findForTravel() {
		// TODO Auto-generated method stub
		return productSchemeDetailDao.findForTravel();
	}

	@Override
	public List<ProductSchemeDetail> findForTaxSaving() {
		// TODO Auto-generated method stub
		return productSchemeDetailDao.findForTaxSaving();
	}

}
