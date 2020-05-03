package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemi.entity.investment.RegistryWish;

@Repository
public interface RegistryWishCrudRepository extends JpaRepository<RegistryWish,Integer>{
	
    public boolean existsByTransactionid(String transactionid);
    
    public RegistryWish findOneByTransactionid(String transactionid);
	
}
