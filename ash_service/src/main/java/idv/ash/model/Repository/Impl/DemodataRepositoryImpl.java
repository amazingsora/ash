package idv.ash.model.Repository.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import idv.ash.model.Entity.Demodata;
import idv.ash.model.Facade.AbstractFacade;

public class DemodataRepositoryImpl extends AbstractFacade<Demodata>{
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DemodataRepositoryImpl() {
		super(Demodata.class);
	}


	public List<Demodata> queryAllForC(Demodata obj) {

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
