package idv.ash.model.Facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import idv.ash.model.Entity.Demodata;

@Repository
public class DemodataFacade extends AbstractFacade<Demodata> {
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DemodataFacade() {
		super(Demodata.class);
	}

	public List<Demodata> queryTest1(Demodata obj) throws Exception {

		int index = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from demodata WHERE 1=1 ");

		// C1
		if (StringUtils.isNotEmpty(obj.getC1())) {
			sql.append(" AND C1 =  ? ");

		}

		Query q = em.createNativeQuery(sql.toString(), Demodata.class);

		if (StringUtils.isNotEmpty(obj.getC1())) {
			q.setParameter(++index, obj.getC1());

		}

		List<Demodata> resultList = (List<Demodata>) q.getResultList();

		return resultList;
	}

}
