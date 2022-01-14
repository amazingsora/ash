package idv.amazingsora.ash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import idv.amazingsora.Service.DemodataService;

@RestController
public class HelloController {
	
	@Autowired
	DemodataService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/angularTest")
    public String getHello() {
        System.out.println("angularTest");
        service.userTest();
        return "1234sss567890";
    }
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/testPass")
    public void testPass(ViewControllerRegistry registry) {
        System.out.println("testPass");
        
	    registry.addViewController("../").setViewName("forward:/testPage/test.html");

    }
    
    
    
    

}