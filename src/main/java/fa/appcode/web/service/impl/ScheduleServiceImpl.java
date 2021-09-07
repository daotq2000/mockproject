package fa.appcode.web.service.impl;

import fa.appcode.web.converter.ScheduleConverter;
import fa.appcode.web.dto.ScheduleDTO;
import fa.appcode.web.entities.Schedule;
import fa.appcode.web.repository.ScheduleRepository;
import fa.appcode.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleConverter scheduleConverter;
    @Override
    public Map<String, Object> paging(int page, int size, String search, String field, String order) {
        return null;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public ScheduleDTO update(ScheduleDTO scheduleDTO, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public ScheduleDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();;
        return schedules.stream().map(schedule->scheduleConverter.convertToDTO(schedule)).collect(Collectors.toList());
    }
}
