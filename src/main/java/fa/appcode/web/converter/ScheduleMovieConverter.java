package fa.appcode.web.converter;

import fa.appcode.web.dto.ScheduleDTO;
import fa.appcode.web.dto.ScheduleMovieDTO;
import fa.appcode.web.entities.Schedule;
import fa.appcode.web.entities.ScheduleMovie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMovieConverter {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired private ScheduleConverter scheduleConverter;
    public ScheduleMovieDTO convertToDTO(ScheduleMovie scheduleMovie){
        ScheduleMovieDTO scheduleMovieDTO = new ScheduleMovieDTO();
        if(scheduleMovie.getScheduleMovieId().getSchedule() != null){
            scheduleMovieDTO.setSchedule(scheduleConverter.convertToDTO(scheduleMovie.getScheduleMovieId().getSchedule()));
        }
        return scheduleMovieDTO;
    }
    public Schedule convertToEntity(ScheduleDTO scheduleDTO){
        return modelMapper.map(scheduleDTO,Schedule.class);
    }
}
