package fa.appcode.web.repository;

import fa.appcode.web.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
}
