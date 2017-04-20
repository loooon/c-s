package com.credit.base;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author preideas
 *Repository基类，注入LocalContainerEntityManagerFactoryBean
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public abstract class AbstractJpaBaseRepository<T, ID extends Serializable>
{
    private static final String PERSIST_UNITNAME = "uzj";
    
    @PersistenceContext(unitName=PERSIST_UNITNAME)
    @Qualifier("entityManagerFactory")
    protected EntityManager entityManager;

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
