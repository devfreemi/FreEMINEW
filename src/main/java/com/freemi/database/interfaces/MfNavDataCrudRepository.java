package com.freemi.database.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freemi.entity.investment.MfNavData;

public interface MfNavDataCrudRepository extends JpaRepository<MfNavData,Long>{
	
	/*@Query(value="select date_format(s.nav_date,'%d-%m-%Y') as navdate, s.nav from daily_nav_sumanta s where s.isind_no = :isin order by s.nav_date asc",nativeQuery=true)
	public List<Object[]> getAllNavOfIsin(@Param("isin") String isin);*/
	
//	@Query("select date_format(s.navdate,'%d-%m-%Y') as navdate, s.navvalue from MfNavData s where s.isin = :isin order by s.navdate asc")
	@Query("select date_format(s.navdate,'%d-%m-%Y') as navdate, s.navvalue from MfNavData s where s.isin = :isin order by s.navdate asc")
	public List<MfNavData> getAllNavOfIsin(@Param("isin") String isin);
	
	@Query("select date_format(s.navdate,'%d-%m-%Y') as navdate, s.navvalue from MfNavData s where s.isin = :isin and s.navdate BETWEEN :startDate AND :endDate order by s.navdate asc")
	public List<MfNavData> get5YearsNavData(@Param("isin") String isin,@Param("startDate")Date startDate,@Param("endDate")Date endDate );
	
	
}
