package idv.ash.model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.ash.model.Entity.Demodata;
import idv.ash.model.Facade.DemodataFacade;
import idv.ash.model.Repository.DemodataRepository;
import idv.ash.model.Repository.Impl.DemodataRepositoryImpl;
import idv.ash.model.Util.JDBCUtil;

@Service
public class DemodataService {

	@Autowired
	private DemodataRepository demodataRepository;
	@Autowired
	private DemodataRepositoryImpl impl;
	
	
	@Autowired
	DemodataFacade demodataFacade;
	public void userTest() {
		System.out.println("進行寫入");
		Demodata user = new Demodata();
		user.setC1("d1");
		user.setC2(1);
		user.setC3("Y");
		user.setC4("clob?");
		demodataRepository.save(user);
		System.out.println("寫入完成");

	}
	public  List<Demodata>  queryC1(String c1) {
		return demodataRepository.queryC1(c1);

	}
	public  List<Demodata>  queryAll2(Demodata obj) throws Exception {
		return demodataFacade.queryTest1(obj);

	}
	public void jdbcTest () {
		JDBCUtil jdbc =new JDBCUtil();
		jdbc.createQuery("select * from demodata where C1 = 'd11' ");
		
	}
	public void queryList (String []value) {
		JDBCUtil jdbc =new JDBCUtil();
		jdbc.createQuery("select * from demodata where C1 = 'd11' ");
		
	}
	
}