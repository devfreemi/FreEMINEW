package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.MapSchemeManager;
@Repository
public interface MapSchemeManagerDao extends JpaRepository<MapSchemeManager, Long> {

}
