package fa.appcode.web.controller;

import fa.appcode.web.converter.CinemaRoomConverter;
import fa.appcode.web.dto.CinemaRoomDTO;
import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/cinemaRoom")
public class RoomController {
    @Autowired
    private CinemaRoomService cinemaRoomService;
    @Autowired private CinemaRoomConverter cinemaRoomConverter;
    @RequestMapping(value = "/{id}")
    public ResponseEntity<CinemaRoomDTO> getByIdRoom(@PathVariable("id")Integer id){
        return new ResponseEntity<>(cinemaRoomService.findById(id), HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<List<CinemaRoomDTO>> findALL(){
    return new ResponseEntity<>(cinemaRoomService.findAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/listRoom")
    public Page<CinemaRoomDTO> getListCinemaRoomPaging(@RequestParam(value = "size",required = false) Integer size,
                                                       @RequestParam(value = "page",required = false)  Integer page,
                                                       @RequestParam(value = "searchNameRoom",required = false) String searchNameRoom){
        Page<CinemaRoom> pageEntity =cinemaRoomService.findAllByNameContaining(size,page,searchNameRoom);
        Page<CinemaRoomDTO> pageDTO = pageEntity.map(cinemaRoom -> cinemaRoomConverter.convertToDTO(cinemaRoom));
        return  pageDTO;
    }
    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    public ResponseEntity<CinemaRoomDTO> addCinemaRoom(@RequestBody CinemaRoomDTO cinemaRoom) throws ParseException {
        return new ResponseEntity<>(cinemaRoomService.save(cinemaRoom), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/editRoom",method = RequestMethod.POST)
    public ResponseEntity<CinemaRoomDTO> updateCinemaRoom(@RequestBody CinemaRoomDTO cinemaRoom){
        return new ResponseEntity<>(cinemaRoomService.update(cinemaRoom,cinemaRoom.getId()), HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteRoom/{id}")
    public ResponseEntity<String> deleteCinemaRoom(@PathVariable("id") Integer id){
        return new ResponseEntity<>(cinemaRoomService.delete(id)?"Delete Success":"Delete Failed", HttpStatus.OK);
    }


}
