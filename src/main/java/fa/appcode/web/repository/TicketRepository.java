package fa.appcode.web.repository;

import fa.appcode.web.entities.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
