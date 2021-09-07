package fa.appcode.web.repository;

import fa.appcode.web.entities.DateMovie;
import fa.appcode.web.entities.DateMovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DateMovieRepository extends JpaRepository<DateMovie, DateMovieId> {
    @Query(value = "delete from date_movie where movie_id=?1", nativeQuery = true)
    @Modifying
    void deleteDate(Integer movieId);
}
