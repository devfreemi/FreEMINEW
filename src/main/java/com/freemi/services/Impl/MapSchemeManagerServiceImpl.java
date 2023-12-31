package com.freemi.services.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemi.database.interfaces.MapSchemeManagerDao;
import com.freemi.database.interfaces.MapSchemeManagerService;
import com.freemi.entity.database.MapSchemeManager;

@Service
public class MapSchemeManagerServiceImpl implements MapSchemeManagerService {

	@Autowired
	MapSchemeManagerDao mapSchemeManagerDao;
	@Override
	public MapSchemeManager save(MapSchemeManager entity) {
		// TODO Auto-generated method stub
		return mapSchemeManagerDao.save(entity);
	}

	@Override
	public MapSchemeManager getById(Serializable id) {
		// TODO Auto-generated method stub
		return mapSchemeManagerDao.getOne((Long) id);
	}

	@Override
	public List<MapSchemeManager> getAll() {
		// TODO Auto-generated method stub
		return mapSchemeManagerDao.findAll();
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//mapSchemeManagerDao.delete((Long) id);
	}

}
