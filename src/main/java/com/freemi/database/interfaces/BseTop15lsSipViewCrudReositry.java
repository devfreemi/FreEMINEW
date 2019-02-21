package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFTop15lsSip;

@Service
public interface BseTop15lsSipViewCrudReositry extends JpaRepository<BseMFTop15lsSip,String>{
	
	
}
