package com.ey.ums.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.ey.ums.model.Cart;
import com.ey.ums.model.CheckOut;
import com.ey.ums.model.Product;
import com.ey.ums.model.UserDtls;
import com.ey.ums.repository.CartRepo;
import com.ey.ums.repository.ProductRepository;
import com.ey.ums.repository.UserRepository;
import com.ey.ums.service.CartService;
import com.ey.ums.service.CheckOutService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	CheckOutService checkOutServ;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		UserDtls user = userRepo.findByEmail(email);

		m.addAttribute("user", user);

	}

//	@GetMapping("/")
//	public String home() {
//		return "/home";
//	}
	@GetMapping("/")
	public String getAllProducts(Model model,@RequestParam(required = false) String code){
		
		if("400".equals(code)) {
            throw new BadRequestException("BAD_CODE");
        }
        
        if("403".equals(code)) {
            throw new AccessDeniedException("You are not authorized to access this page");
        }
        
        if("404".equals(code)) {
            throw new PageNotFoundException("This page is not found!!");
        }
        
        if("500".equals(code)) {
            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
        }
		List<Product> list = restTemplate.getForObject("http://localhost:9001/home/productList", List.class);
		System.out.println("-----------------------------------------------=============================================hELLO");
		model.addAttribute("products",list);
		System.out.println( model.getAttribute("products"));
		
		return "index1"; 
		
	}
	
	@GetMapping("/addToViewById/{id}")
    public String updateForm(@PathVariable Long id,Model model,@RequestParam(required = false) String code) {
		if("400".equals(code)) {
            throw new BadRequestException("BAD_CODE");
        }
        
        if("403".equals(code)) {
            throw new AccessDeniedException("You are not authorized to access this page");
        }
        
        if("404".equals(code)) {
            throw new PageNotFoundException("This page is not found!!");
        }
        
        if("500".equals(code)) {
            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
        }
        Product product= productRepo.getById(id);
        
//        Cart newCart = new Cart();
//        newCart.setProduct(product);
        model.addAttribute("products", product);
        return "viewProductPage";
    }
	
	@GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id,Model model,@RequestParam(required = false) String code) {
		
		if("400".equals(code)) {
            throw new BadRequestException("BAD_CODE");
        }
        
        if("403".equals(code)) {
            throw new AccessDeniedException("You are not authorized to access this page");
        }
        
        if("404".equals(code)) {
            throw new PageNotFoundException("This page is not found!!");
        }
        
        if("500".equals(code)) {
            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
        }
        Product product= productRepo.findById(id).get();
        
        Cart newCart = new Cart();
        newCart.setOrderId((long) 1);
        newCart.setProduct(product);
        System.out.println(newCart.getProduct());
        cartRepo.save(newCart);
        List<Cart> cartItems = List.of(newCart);
        model.addAttribute("products", cartService.getAllCartItems());
        model.addAttribute("total", cartService.getSum());
        return "addToCartPage";
        
        
            
           
           
        
    }
	
	@GetMapping("/addTocart")
	public String redirectToCart(Model model,@RequestParam(required = false) String code) {
		if("400".equals(code)) {
            throw new BadRequestException("BAD_CODE");
        }
        
        if("403".equals(code)) {
            throw new AccessDeniedException("You are not authorized to access this page");
        }
        
        if("404".equals(code)) {
            throw new PageNotFoundException("This page is not found!!");
        }
        
        if("500".equals(code)) {
            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
        }
	    model.addAttribute("products", cartService.getAllCartItems());
	    model.addAttribute("total", cartService.getSum());
	    return "addToCartPage";
	}




	    @GetMapping("/deleteUserOrder/{orderid}")
	    public String deleteUserOrder(@PathVariable Long orderid,@RequestParam(required = false) String code) {
	    	if("400".equals(code)) {
	            throw new BadRequestException("BAD_CODE");
	        }
	        
	        if("403".equals(code)) {
	            throw new AccessDeniedException("You are not authorized to access this page");
	        }
	        
	        if("404".equals(code)) {
	            throw new PageNotFoundException("This page is not found!!");
	        }
	        
	        if("500".equals(code)) {
	            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
	        }
	        cartService.deleteOrder(orderid);
	        return "redirect:/user/addTocart";
	    }
	    
	    @GetMapping("/clearCart")
	    public String clearCart(@RequestParam(required = false) String code) {
	    	if("400".equals(code)) {
	            throw new BadRequestException("BAD_CODE");
	        }
	        
	        if("403".equals(code)) {
	            throw new AccessDeniedException("You are not authorized to access this page");
	        }
	        
	        if("404".equals(code)) {
	            throw new PageNotFoundException("This page is not found!!");
	        }
	        
	        if("500".equals(code)) {
	            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
	        }
	        cartService.deleteCart();
	        
	        return "clearCartPage";
	    }
	
	    
	    @GetMapping("/billGenerate")
	    public String billGenerate(Model model,@RequestParam(required = false) String code) {
	    	if("400".equals(code)) {
	            throw new BadRequestException("BAD_CODE");
	        }
	        
	        if("403".equals(code)) {
	            throw new AccessDeniedException("You are not authorized to access this page");
	        }
	        
	        if("404".equals(code)) {
	            throw new PageNotFoundException("This page is not found!!");
	        }
	        
	        if("500".equals(code)) {
	            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
	        }
	        model.addAttribute("total", cartService.getSum());
	        CheckOut checkOut = new CheckOut();
	        model.addAttribute("checkOut", checkOut);
	        return "checkOut";
	    }
	    
	    @PostMapping("/checkOutBill")
	    public String checkOutBill(@ModelAttribute CheckOut checkOut,@RequestParam(required = false) String code) {
	    	if("400".equals(code)) {
	            throw new BadRequestException("BAD_CODE");
	        }
	        
	        if("403".equals(code)) {
	            throw new AccessDeniedException("You are not authorized to access this page");
	        }
	        
	        if("404".equals(code)) {
	            throw new PageNotFoundException("This page is not found!!");
	        }
	        
	        if("500".equals(code)) {
	            throw new InternalServerException("Internal Server Occured, Please go check your controller code!!");
	        }
	        checkOutServ.saveBill(checkOut);
	        return "Finish";
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
        class BadRequestException extends RuntimeException{
            
            public BadRequestException(String message) {
                super(message);
            }
        }



       @ResponseStatus(HttpStatus.FORBIDDEN)
        class AccessDeniedException extends RuntimeException{
            
            public AccessDeniedException(String message) {
                super(message);
            }
            
        }
        @ResponseStatus(HttpStatus.NOT_FOUND)
        class PageNotFoundException extends RuntimeException{
            
            public PageNotFoundException(String message) {
                super(message);
            }
            
        }



       @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        class InternalServerException extends RuntimeException{
            
            public InternalServerException(String message) {
                super(message);
            }
            
       
        }
    

	

	
	

}
