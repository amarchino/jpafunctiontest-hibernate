package it.test.amarchino.jpafunctiontesthibernate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JpaFunctionRepository extends Repository<JpaFunctionEntity, Integer> {

    @Query(" SELECT a FROM generate_series(CAST(:min AS INTEGER), CAST(:max AS INTEGER)) a ")
	List<Integer> generateSeries(@Param("min") Integer min, @Param("max") Integer max);
}
