package com.credit.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author preideas
 *JPA通用业务仓库
 *
 * @param <T>
 * @param <ID>
 */
public interface JpaBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>
{
}
