package fa.appcode.web.repository;

import fa.appcode.web.entities.TypeMovie;
import fa.appcode.web.entities.TypeMovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMovieRepository extends JpaRepository<TypeMovie, TypeMovieId> {
    @Query(value = "delete from type_movie where movie_id=?1", nativeQuery = true)
    @Modifying
    void deleteType(Integer movieId);
}
