package com.credit.repository;

import java.util.List;

import com.credit.base.JpaBaseRepository;
import com.credit.entity.Score;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Michael Chan on 3/27/2017.
 */
public interface ScoreRepository extends JpaBaseRepository<Score, Integer>
{
	@Query(value = "select score from tb_score where var_name = ?1 and min <= ?2 and max >=?2",nativeQuery = true)
    Double findByVarNameAndValueIn(String varName, Integer value);
}
