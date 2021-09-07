package fa.appcode.web.repository;

import fa.appcode.web.entities.DateMovie;
import fa.appcode.web.entities.DateMovieId;
import fa.appcode.web.entities.ShowDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ShowDateMovieRepository extends JpaRepository<DateMovie, DateMovieId> {
    @Query(value = "select d from DateMovie d where d.dateMovieId.date.dateName like %:search%")
    Page<DateMovie> searchMovieByDateName(Pageable pageable, String search);

}
