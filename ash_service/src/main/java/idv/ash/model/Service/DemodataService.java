package idv.ash.model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.ash.model.Entity.Demodata;
import idv.ash.model.Repository.DemodataRepository;

@Service
public class DemodataService {

	@Autowired
	private DemodataRepository demodataRepository;

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
}