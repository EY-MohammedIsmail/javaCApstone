package com.ey.ums.service;


import java.util.List;

import com.ey.ums.model.Product;
import com.ey.ums.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);
	public Product getById(Long id);
	public List<UserDtls> getAllUserDetails();
	public void saveAll(Product product);
	public void deleteUserById(Integer id);
	public UserDtls getUserById(Integer id);

	public void saveAllUser(UserDtls userr);
}