package com.ey.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ey.ums.controller.AdminController.AccessDeniedException;
import com.ey.ums.controller.AdminController.BadRequestException;
import com.ey.ums.controller.AdminController.InternalServerException;
import com.ey.ums.controller.AdminController.PageNotFoundException;
import com.ey.ums.model.Billing;
import com.ey.ums.service.BillingService;
import com.ey.ums.service.CartService;

@Controller
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
    BillingService billService;
    @Autowired
    CartService cartService;
    
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
        Billing order = new Billing();
        model.addAttribute("checkOut", order);
        return "checkOut";
    }
    @PostMapping("/checkOutBill")
    public String checkOutBill(@ModelAttribute Billing bill,@RequestParam(required = false) String code) {
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
        billService.saveOrder(bill);
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
