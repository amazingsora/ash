package idv.amazingsora.ash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class HomeController implements WebMvcConfigurer {

  
	@GetMapping("/")
    public String home() {
    	System.out.println("ssssssss");
        return "forward:/index.html";
    }
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("導向入口");
	    registry.addViewController("/").setViewName("forward:/index.html");
	}

}