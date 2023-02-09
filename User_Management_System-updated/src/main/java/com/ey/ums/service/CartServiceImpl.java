package com.ey.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.ums.model.Cart;
import com.ey.ums.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepo cartRepo;
	@Override
	public List<Cart> getAllCartItems() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}
	@Override
	public void deleteOrder(Long orderid) {
		// TODO Auto-generated method stub
		 cartRepo.deleteById(orderid);
		
	}
	@Override
	public void deleteCart() {
		// TODO Auto-generated method stub
		cartRepo.deleteAll();
		
		
	}
	@Override
	public Long getSum() {
		 List<Cart> list    = cartRepo.findAll();
		    Long sum=0L;
		    for(Cart c : list) {
		        sum = sum + c.getProduct().getProductPrice();
		    }
		        return sum;
		
	}
	
	

}
