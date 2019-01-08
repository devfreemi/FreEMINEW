package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.database.MfTopFundsInventory;

public interface TopFundsRepository extends JpaRepository<MfTopFundsInventory, String> {

}
