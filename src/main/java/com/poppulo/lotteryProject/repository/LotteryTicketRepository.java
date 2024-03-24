package com.poppulo.lotteryProject.repository;

import com.poppulo.lotteryProject.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryTicketRepository extends CrudRepository<Ticket, Long> {
}
