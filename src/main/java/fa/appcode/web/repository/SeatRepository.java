package fa.appcode.web.repository;

import fa.appcode.web.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    @Modifying
    @Query(value = "delete from Seat where cinemaroom_id =:id",nativeQuery = true)
    void deleteAllByCinemaRoomId(int id);

}
