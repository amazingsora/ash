package idv.amazingsora.ash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import idv.ash.model.Entity.Demodata;
import idv.ash.model.Service.DemodataService;
import net.sf.json.JSONArray;

@RestController
public class HelloController {
	
	@Autowired
	DemodataService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/angularTest")
    public String getHello() {
        System.out.println("angularTest");
//        service.userTest();
//        List<Demodata> data1 = service.queryAll1("d1");
//        
//        JSONArray j1 =  JSONArray.fromObject(data1);
        service.jdbcTest();
        return "測試";
    }
	@GetMapping("/queryTest")
    public String query() {
        System.out.println("query");
        List<Demodata> data1 = service.queryAll1("d1");
        
        JSONArray j1 =  JSONArray.fromObject(data1);
        
        return j1.toString();
    }
	@GetMapping("/queryTest2")
    public String query2() throws Exception {
        System.out.println("query2");
        Demodata search  = new Demodata();
        search.setC1("d1");
        List<Demodata> data1 = service.queryAll2(search);
        
        JSONArray j1 =  JSONArray.fromObject(data1);
        
        return j1.toString();
    }
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/testPass")
    public void testPass(ViewControllerRegistry registry) {
        System.out.println("testPass");
        
	    registry.addViewController("../").setViewName("forward:/testPage/test.html");

    }
    
    
    
    

}