package org.model.Service;

import org.model.Entity.Demodata;
import org.model.Repository.DemodataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DemodataService  {

    @Autowired
    private DemodataRepository demodataRepository;
    public void userTest() {
    	System.out.println("進行寫入");
    	Demodata user = new Demodata();
        user.setC1("d1");
        user.setC2(1);
        user.setC3("true");
        user.setC4("clob?");
        demodataRepository.save(user);
    	System.out.println("寫入完成");

      
    }
}