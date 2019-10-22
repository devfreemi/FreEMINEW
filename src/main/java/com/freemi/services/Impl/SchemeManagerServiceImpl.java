package com.freemi.services.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.SchemeManagerDao;
import com.freemi.database.interfaces.SchemeManagerService;
import com.freemi.entity.database.SchemeManager;

@Service
public class SchemeManagerServiceImpl implements SchemeManagerService {

	@Autowired
	SchemeManagerDao schemeManagerDao;
	@Override
	public SchemeManager save(SchemeManager entity) {
		// TODO Auto-generated method stub
		return schemeManagerDao.save(entity);
	}

	@Override
	public SchemeManager getById(Serializable id) {
		// TODO Auto-generated method stub
		return schemeManagerDao.getOne((Long) id);
	}

	@Override
	public List<SchemeManager> getAll() {
		// TODO Auto-generated method stub
		return schemeManagerDao.findAll();
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		schemeManagerDao.delete((Long) id);
	}

}
