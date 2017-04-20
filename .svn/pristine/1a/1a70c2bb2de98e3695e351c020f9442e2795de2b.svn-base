package com.credit.base;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

/**
 *
 * 重写CRUD中的分页查询方法，默认只查询一次总量
 * 重写方法：
 *
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
public class JpaBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
        JpaBaseRepository<T, ID>
{

    private EntityManager entityManager;


    // There are two constructors to choose from, either can be used.
    public JpaBaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager)
    {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class
        // dependencies.
        this.entityManager = entityManager;
    }
    
    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable)
    {
        TypedQuery<T> query = getQuery(spec, pageable);
        return pageable == null ? new PageImpl<T>(query.getResultList()) : this.readPage(query, pageable, spec);
    }
    
    @Override
    public Page<T> findAll(Pageable pageable)
    {
        if (null == pageable) 
		{
            return new PageImpl<T>(findAll());
        }

        return this.findAll(null, pageable);
    }
    
    
    
    @Override
    protected Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec)
    {

        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        
        //新增逻辑，防止多次执行count操作
        Long total = null;
        if(pageable instanceof BasePageRequest)
        {
            BasePageRequest bpq = (BasePageRequest)pageable;
            if(bpq.getTotal() > 0)
            {
                total = bpq.getTotal();
            }
        }
        
        //如果分页请求中总数为0，则执行count操作
        if(null == total || total == 0)
        {
            total = executeCountQuery(getCountQuery(spec));
        }
        
        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();

        return new PageImpl<T>(content, pageable, total);
    }
    
    private static Long executeCountQuery(TypedQuery<Long> query)
	{

        Assert.notNull(query);

        List<Long> totals = query.getResultList();
        Long total = 0L;

        for (Long element : totals)
		{
            total += element == null ? 0 : element;
        }

        return total;
    }
}
