package fa.appcode.web.repository;

import fa.appcode.web.entities.Schedule;
import fa.appcode.web.entities.ScheduleMovie;
import fa.appcode.web.entities.ScheduleMovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleMovieRepository extends JpaRepository<ScheduleMovie, ScheduleMovieId> {
    @Query(value = "delete from schedule_movie where movie_id=?1", nativeQuery = true)
    @Modifying
    void deleteSchedule(Integer movieId);
}
