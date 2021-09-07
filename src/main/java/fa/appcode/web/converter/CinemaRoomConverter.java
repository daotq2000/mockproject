package fa.appcode.web.converter;

import fa.appcode.web.dto.CinemaRoomDTO;
import fa.appcode.web.dto.SeatDTO;
import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.entities.Seat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaRoomConverter {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired private SeatConverter seatConverter;
    public CinemaRoomDTO convertToAllDependencies(CinemaRoom cinemaRoom){
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setId(cinemaRoom.getId());
        cinemaRoomDTO.setRoomName(cinemaRoom.getRoomName());
        cinemaRoomDTO.setSeatQuantity(cinemaRoom.getSeatQuantity());
        List<SeatDTO> seats = new ArrayList<>();
        cinemaRoom.getSeats().forEach(seat -> seats.add(seatConverter.convertToDTO(seat)));
        cinemaRoomDTO.setSeats(seats);
        return cinemaRoomDTO;
    }
    public CinemaRoomDTO convertToDTO(CinemaRoom cinemaRoom){
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setId(cinemaRoom.getId());
        cinemaRoomDTO.setRoomName(cinemaRoom.getRoomName());
        cinemaRoomDTO.setSeatQuantity(cinemaRoom.getSeatQuantity());
        return cinemaRoomDTO;
    }
    public CinemaRoom convertToEntity(CinemaRoomDTO cinemaRoomDTO){
        CinemaRoom cinemaRoom = modelMapper.map(cinemaRoomDTO,CinemaRoom.class);
        return cinemaRoom;
    }
}
