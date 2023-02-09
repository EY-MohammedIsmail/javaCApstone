package com.ey.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.ums.model.CheckOut;

@Repository
public interface CheckOutRepo extends JpaRepository<CheckOut, Long>  {

}
