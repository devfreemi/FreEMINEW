package com.freemi.database.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.MFCamsFolio;

@Service
public interface MfCamsFolioCrudRepository extends JpaRepository<MFCamsFolio,Long> {
	
	public List<MFCamsFolio> findAllByPan(String pan);
	
	public MFCamsFolio findOneByFolioNumberAndRtaCode(String folio, String rtaCode);

}
