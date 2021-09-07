package fa.appcode.web.converter;

import fa.appcode.web.dto.ScheduleDTO;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.Schedule;
import fa.appcode.web.entities.ScheduleSeat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleSeatConverter {
    @Autowired
    private CinemaRoomConverter cinemaRoomConverter;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private ScheduleConverter scheduleConverter;
    private ModelMapper modelMapper = new ModelMapper();

    public ScheduleSeatDTO convertToDTO(ScheduleSeat scheduleSeat) {
        ScheduleSeatDTO scheduleSeatDTO = new ScheduleSeatDTO();
        scheduleSeatDTO.setScheduleSeatId(scheduleSeat.getScheduleSeatId());
        scheduleSeatDTO.setStatus(scheduleSeat.getStatus());
        scheduleSeatDTO.setSeatRow(scheduleSeat.getSeatRow());
        scheduleSeatDTO.setSeatColumn(scheduleSeat.getSeatColumn());
        scheduleSeatDTO.setSeatId(scheduleSeat.getSeatId());
        scheduleSeatDTO.setSeatType(scheduleSeat.getSeatType());
        scheduleSeatDTO.setStatus(scheduleSeat.getStatus());
//        scheduleSeatDTO.setMovie(movieConverter.convertToDTO(scheduleSeat.getMovie()));
//        scheduleSeatDTO.setSchedule(scheduleConverter.convertToDTO(scheduleSeat.getSchedule()));
        return scheduleSeatDTO;
    }

    public ScheduleSeat convertToEntity(ScheduleSeatDTO scheduleSeatDTO) {
        ScheduleSeat scheduleSeat = modelMapper.map(scheduleSeatDTO, ScheduleSeat.class);
        return scheduleSeat;
    }
}
