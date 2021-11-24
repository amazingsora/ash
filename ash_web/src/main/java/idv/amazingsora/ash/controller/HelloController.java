package idv.amazingsora.ash.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/angularTest")
    public String getHello() {
        System.out.println("angularTest");
        return "1234sss567890";
    }
    
    
    
    

}