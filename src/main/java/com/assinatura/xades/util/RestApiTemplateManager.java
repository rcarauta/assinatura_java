package com.assinatura.xades.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class RestApiTemplateManager {
	
	@PersistenceContext
	private EntityManager entityManager;


	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}
