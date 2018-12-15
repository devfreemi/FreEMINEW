package com.freemi.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freemi.entity.investment.FolioCreationStatus;

public interface RegistryPurchaseRecords extends JpaRepository<FolioCreationStatus, Integer> {

}
