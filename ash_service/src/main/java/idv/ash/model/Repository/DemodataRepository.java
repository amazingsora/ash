package idv.ash.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idv.ash.model.Entity.Demodata;
@Repository
public interface DemodataRepository extends JpaRepository<Demodata, Long> {

}