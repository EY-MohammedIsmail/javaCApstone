package com.ey.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ey.ums.controller.UserController.AccessDeniedException;
import com.ey.ums.controller.UserController.BadRequestException;
import com.ey.ums.controller.UserController.InternalServerException;
import com.ey.ums.controller.UserController.PageNotFoundException;
import com.ey.ums.csvResponsMsg.ResponseMessage;
import com.ey.ums.helper.CSVHelper;
import com.ey.ums.model.Product;
import com.ey.ums.model.UserDtls;
import com.ey.ums.service.CSVService;
import com.ey.ums.service.UserService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	RestTemplate restTemplate;
	

	@Autowired
	UserService service;
	
	
	@GetMapping("/")
	public String home(@RequestParam(required = false) String code) {
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
		return "adminn/index";
	}
	
	@GetMapping("/product")
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
		model.addAttribute("products",list);
		model.addAttribute("userList", service.getAllUserDetails());
		return "adminn/product"; 
		
	}
	
	@PostMapping("/insert")
    public String insertDetails(@ModelAttribute Product product,@RequestParam(required = false) String code) {
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
        service.saveAll(product);
        return "redirect:/admin/product";
        
    }
	
	@GetMapping("/deleteProduct/{productId}")
	public String deleteProductById(@PathVariable("productId")Integer productId,@RequestParam(required = false) String code){
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
		restTemplate.delete("http://localhost:9001/home/deleteProductById/"+productId);
		
		return "redirect:/admin/product";
		
	}
	@GetMapping("/UpdateForm/{id}")
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
        Product product= service.getById(id);
        model.addAttribute("UpdateObj", product);
        return "adminn/newUpdateForm";
    }
	
	
	
	@GetMapping("/show")
	public String show(Model model,@RequestParam(required = false) String code) {
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
		Product product = new Product();
		model.addAttribute("proObj", product);
		return "adminn/AddForm";
	}
	
	@GetMapping("/user")
    public String getAllUsers(Model model,@RequestParam(required = false) String code) {
		
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
        model.addAttribute("userList", service.getAllUserDetails());
        return "adminn/user";
    }

	@GetMapping("/deleteUser/{userId}")
	public String deleteUserById(@PathVariable("userId") Integer userId,@RequestParam(required = false) String code) {
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
		service.deleteUserById(userId);
		return "redirect:/admin/user";
	}
//	@GetMapping("/UpdateForm/{id}")
//	public String updateForm(@PathVariable Integer id,Model model) {
//		Emp emp= empService.getById(id);
//		model.addAttribute("UpdateObj", emp);
//		return "NewUpdateForm";
//	}
	
	@GetMapping("/UpdateUserForm/{id}")
	public String updateUserForm(@PathVariable Integer id,Model model,@RequestParam(required = false) String code)
	{
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
		UserDtls dtls = service.getUserById(id);
		model.addAttribute("UpdateUserObj", dtls);
		return "adminn/NewUpdateUserForm";
	}
	
	@PostMapping("/insertUser")
	public String insertDetails(@ModelAttribute UserDtls userr,@RequestParam(required = false) String code) {
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
		service.saveAllUser(userr);
		 return "redirect:/admin/user";
		
	}
	 @Autowired
	    CSVService fileService;
	    
	    @RequestMapping("/csv")
	    public String showIndexPage() {
	    	return "index2";
	    }
	    

	    @PostMapping("/csv/upload")
	    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	      String message = "";

	      if (CSVHelper.hasCSVFormat(file)) {
	        try {
	        	
	          fileService.save(file);

	          message = "Uploaded the file successfully: " + file.getOriginalFilename();
	          
	          String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                  .path("/api/csv/download/")
	                  .path(file.getOriginalFilename())
	                  .toUriString();

	          return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
	        } catch (Exception e) {
	          message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
	        }
	      }

	      message = "Please upload a csv file!";
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
	    }

	    @GetMapping("/csv/tutorials")
	    public ResponseEntity<List<Product>> getAllTutorials() {
	      try {
	        List<Product> tutorials = fileService.getAllTutorials();

	        if (tutorials.isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(tutorials, HttpStatus.OK);
	      } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	    }

	    @GetMapping("/csv/download/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
	      InputStreamResource file = new InputStreamResource(fileService.load());

	      return ResponseEntity.ok()
	          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
	          .contentType(MediaType.parseMediaType("application/csv"))
	          .body(file);
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
