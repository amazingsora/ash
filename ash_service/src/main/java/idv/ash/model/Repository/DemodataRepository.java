package idv.ash.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import idv.ash.model.Entity.Demodata;
@Repository
public interface DemodataRepository extends JpaRepository<Demodata, Long> {
	// 自定義SQL查詢
    @Query(value = "select * from demodata WHERE C1 = ?1 ", nativeQuery = true)
    List<Demodata> queryAllFor(String c1);
}