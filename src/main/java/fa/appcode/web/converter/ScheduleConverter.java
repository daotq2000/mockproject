package fa.appcode.web.converter;

import fa.appcode.web.dto.ScheduleDTO;
import fa.appcode.web.entities.Schedule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {
    ModelMapper modelMapper = new ModelMapper();
    public ScheduleDTO convertToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setTime(schedule.getTime());
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }
    public Schedule convertToEntity(ScheduleDTO scheduleDTO){
        return modelMapper.map(scheduleDTO,Schedule.class);
    }
}
