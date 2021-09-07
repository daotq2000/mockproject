package fa.appcode.web.repository;

import fa.appcode.web.entities.ShowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface ShowDateRepository extends JpaRepository<ShowDate,Integer> {
    boolean existsByDate(LocalDate localDate);

    ShowDate findByDate(LocalDate date);
}
