package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.SchemeManager;
@Repository
public interface SchemeManagerDao extends JpaRepository<SchemeManager, Long> {

}
