package com.freemi.database.respository.mahindra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.freemi.entity.investment.mahindra.MahindraFDSaveLeadDetails;

public interface MMLeadDetailsCrudRepository extends JpaRepository<MahindraFDSaveLeadDetails, Long> {
	
	public boolean existsByMobile(String mobile);
	public boolean existsByCpTransRefNo(String cpRefNo);
	
	public MahindraFDSaveLeadDetails findOneByCpTransRefNo(String cpTransRefNo);
	
	@Transactional
	@Modifying
	@Query("update MahindraFDSaveLeadDetails b set b.mfSysRefNo= :refNo, b.apiCallComplete= 'Y' where b.cpTransRefNo= :cpRefNo ")
	public int updateMfSysRefNo(@Param("refNo") String mfSysRefNo, @Param("cpRefNo") String cpRefNo );
	
	
	
}
