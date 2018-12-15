package com.freemi.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.SchemeMaster;
@Repository
public interface SchemeMasterDao extends JpaRepository<SchemeMaster, Long> {

}
