package com.ey.ums.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.ums.model.CheckOut;
import com.ey.ums.repository.CheckOutRepo;

@Service
public class CheckOutServiceImpl implements CheckOutService {
	
	  @Autowired
	    CheckOutRepo checkOutRepository;

	@Override
	public void saveBill(CheckOut checkOut) {
		checkOutRepository.save(checkOut);
		
		
		
	}

}
