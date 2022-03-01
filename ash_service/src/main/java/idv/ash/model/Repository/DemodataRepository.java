package idv.ash.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import idv.ash.model.Entity.Demodata;
@Repository
public interface DemodataRepository extends BaseRepository<Demodata,Long> {
	// 自定義SQL查詢
    @Query(value = "select * from demodata WHERE C1 = ?1 ", nativeQuery = true)
    List<Demodata> queryC1(String c1);
}