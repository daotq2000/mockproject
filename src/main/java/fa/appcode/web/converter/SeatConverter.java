package fa.appcode.web.converter;

import fa.appcode.web.dto.SeatDTO;
import fa.appcode.web.entities.Seat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SeatConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public SeatDTO convertToDTO(Seat seat){
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setStatus(seat.getStatus());
        seatDTO.setSeatColumn(seat.getSeatColumn());
        seatDTO.setSeatId(seat.getSeatId());
        seatDTO.setSeatPrice(seat.getSeatPrice());
        seatDTO.setSeatRow(seat.getSeatRow());
        seatDTO.setSeatType(seat.getSeatType());
        seatDTO.setCinemaRoom(null);
        return seatDTO;
    }
    public Seat convertToEntity(SeatDTO seatDTO){
        Seat seat = modelMapper.map(seatDTO,Seat.class);
        return seat;
    }
}
