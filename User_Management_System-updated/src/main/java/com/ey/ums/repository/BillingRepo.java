package com.ey.ums.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ey.ums.model.Billing;


public interface BillingRepo extends MongoRepository<Billing, Integer> {

}




