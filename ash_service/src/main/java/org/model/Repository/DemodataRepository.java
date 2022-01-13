package org.model.Repository;

import org.model.Entity.Demodata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemodataRepository extends JpaRepository<Demodata, Long> {

	Demodata findByUserName(String seq);

}