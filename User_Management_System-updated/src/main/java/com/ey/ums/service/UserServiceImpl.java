package com.ey.ums.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.ums.model.Product;
import com.ey.ums.model.UserDtls;
import com.ey.ums.repository.ProductRepository;
import com.ey.ums.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserDtls createUser(UserDtls user) {

		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");

		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {

		return userRepo.existsByEmail(email);
	}

	@Override
	public Product getById(Long id) {
		
		Product product=null;
        Optional<Product> optional= productRepository.findById(id);
        if(optional.isPresent()) {
            product = optional.get();
        }
        return product;
	}
	@Override
	public void saveAll(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public List<UserDtls> getAllUserDetails() {
		
		return userRepo.findAll();
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepo.deleteById(id);
		
	}

	@Override
	public UserDtls getUserById(Integer id) {
		UserDtls userr=null;
		Optional<UserDtls> optional= userRepo.findById(id);
		if(optional.isPresent()) {
			userr = optional.get();
		}
		return userr;
	
	}

	@Override
	public void saveAllUser(UserDtls userr) {
		userRepo.save(userr);
		
	}

}