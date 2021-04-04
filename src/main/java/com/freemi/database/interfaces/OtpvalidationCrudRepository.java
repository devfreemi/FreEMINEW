package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.database.Mobileotpverifier;

@Repository
public interface OtpvalidationCrudRepository extends JpaRepository<Mobileotpverifier,Integer>{
	
	public List<Mobileotpverifier> findAllByMobileAndSubmoduleAndSessionidAndOtp(String mobile, String submodule, String sessionid,String otp);
	
	public boolean existsBySessionidAndMobileAndOtpverified(String sessionid, String mobile, String verifiedflag);
}
