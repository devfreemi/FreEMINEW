package com.freemi.services.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.SchemeMasterDao;
import com.freemi.database.interfaces.SchemeMasterService;
import com.freemi.entity.database.SchemeMaster;

@Service
public class SchemeMasterServiceImpl implements SchemeMasterService {

	@Autowired
	SchemeMasterDao schemeMasterDao;
	@Override
	public SchemeMaster save(SchemeMaster entity) {
		// TODO Auto-generated method stub
		return schemeMasterDao.save(entity);
	}

	@Override
	public SchemeMaster getById(Serializable id) {
		// TODO Auto-generated method stub
		return schemeMasterDao.getOne((Long) id);
	}

	@Override
	public List<SchemeMaster> getAll() {
		// TODO Auto-generated method stub
		return schemeMasterDao.findAll();
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//schemeMasterDao.delete((Long) id);
	}

}
