package com.ey.ums.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.ums.model.Billing;
import com.ey.ums.repository.BillingRepo;


@Service
public class BillingServImpl implements BillingService {
	
	 @Autowired
	 BillingRepo billRepository;

	@Override
	public void saveOrder(Billing billing) {
		billRepository.save(billing);
		
		
	}
	
	

}
