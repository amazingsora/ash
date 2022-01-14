package idv.amazingsora.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import idv.amazingsora.Entity.Demodata;
@Component
@Repository("PersonSpringDataJPADao")
public interface DemodataRepository extends JpaRepository<Demodata, Long> {

}