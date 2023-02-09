package com.ey.ums.service;

import java.util.List;


import com.ey.ums.model.Cart;

public interface CartService {
	
	public List<Cart> getAllCartItems();

	public void deleteOrder(Long orderid);

	public void deleteCart();

	public Long getSum();

}
