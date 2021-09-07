package fa.appcode.web.service;

import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.ScheduleSeat;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleSeatService extends BaseService<ScheduleSeatDTO,Integer>{
    List<ScheduleSeatDTO> getAllSeatByMovieIdAndScheduleId(Integer movieId, Integer scheduleId);
    List<ScheduleSeatDTO> getAllSeatBooking(LocalDate localDate,int movieId,int scheduleId);
}
