package fa.appcode.web.service;

import fa.appcode.web.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService extends BaseService<ScheduleDTO,Integer> {
    List<ScheduleDTO> findAll();
}
