package com.poppulo.lotteryProject.repository;

import com.poppulo.lotteryProject.model.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LotteryLineRepository extends CrudRepository<Line, Long> {

}
