package fa.appcode.web.service;

import fa.appcode.web.dto.CinemaRoomDTO;
import fa.appcode.web.entities.CinemaRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.util.List;

public interface CinemaRoomService extends BaseService<CinemaRoomDTO,Integer> {
//    Page<CinemaRoom> findAll(Pageable pageable);
    List<CinemaRoomDTO> findAll();
//    List<CinemaRoom> findAllScroll(int start, int limit);
  void deleteSeatsByCinemaRoomId(Integer id);
  CinemaRoomDTO findById(Integer id);
  Page<CinemaRoom> findAllByNameContaining(Integer size,Integer page,String searchNameRoom);
  public boolean delete(Integer id);
//    CinemaRoom findCinemaRoomById(Integer id);
//    Page<CinemaRoom> findAllByNameContaining(Pageable pageable, String name);
//    List<CinemaRoom> findAllByNameContaining(String name);
//    List<CinemaRoom> findBlogByCategory(int id );

}
