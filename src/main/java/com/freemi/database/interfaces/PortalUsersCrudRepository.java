package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.PortalUsers;

@Repository
public interface PortalUsersCrudRepository extends JpaRepository<PortalUsers,Integer>{
	
	
}
