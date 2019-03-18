package com.freemi.database.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

public interface CRUDService<E> {
   @Transactional
	E save(E entity);

	E getById(Serializable id);

	List<E> getAll();

	void delete(Serializable id);
}
