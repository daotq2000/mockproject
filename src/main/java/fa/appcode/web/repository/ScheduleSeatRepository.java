package fa.appcode.web.repository;

import fa.appcode.web.entities.Movie;
import fa.appcode.web.entities.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat,Integer> {

    List<ScheduleSeat> getByMovieIdAndScheduleId(Integer MovieId,Integer ScheduleId);
    List<ScheduleSeat> getByDateBookingAndMovieIdAndScheduleId(LocalDate DateBooking, int MovieId,int ScheduleId);
}
