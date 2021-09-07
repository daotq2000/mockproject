package fa.appcode.web.repository;

import fa.appcode.web.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
     @Query(value = "select m from Movie m where (m.nameVN like %:search% or m.nameEng like  %:search%)")
     List<Movie> searchAllMovie(String search);

}
