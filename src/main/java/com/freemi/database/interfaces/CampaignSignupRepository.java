package com.freemi.database.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.freemi.entity.database.CampaignSignupForm;

@Service
public interface CampaignSignupRepository extends JpaRepository<CampaignSignupForm, Integer> {

}
