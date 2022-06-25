package idv.ash.model.Repository.Impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import idv.ash.model.Repository.BaseRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	   public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	private EntityManager entityManager;


	}

