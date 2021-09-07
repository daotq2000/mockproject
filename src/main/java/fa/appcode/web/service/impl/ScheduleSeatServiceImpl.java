package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.ScheduleSeatConverter;
import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.entities.ScheduleSeat;
import fa.appcode.web.repository.ScheduleSeatRepository;
import fa.appcode.web.service.ScheduleSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleSeatServiceImpl implements ScheduleSeatService {
    @Autowired
    private ScheduleSeatConverter scheduleSeatConverter;
    @Autowired
    private ScheduleSeatRepository scheduleSeatRepository;
    @Autowired
    public ScheduleSeatServiceImpl(ScheduleSeatConverter scheduleSeatConverter) {
        this.scheduleSeatConverter = scheduleSeatConverter;
    }

    @Override
    public List<ScheduleSeatDTO> getAllSeatByMovieIdAndScheduleId(Integer movieId, Integer scheduleId) {
        List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.getByMovieIdAndScheduleId(movieId, scheduleId);
        List<ScheduleSeatDTO> scheduleSeatDTOS = new ArrayList<>();
        scheduleSeats.forEach(scheduleSeat -> scheduleSeatDTOS.add(scheduleSeatConverter.convertToDTO(scheduleSeat)));
        return scheduleSeatDTOS;
    }

    @Override
    public List<ScheduleSeatDTO> getAllSeatBooking(LocalDate localDate, int movieId, int scheduleId) {
        List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.getByDateBookingAndMovieIdAndScheduleId(localDate, movieId, scheduleId);
        return  scheduleSeats.stream().map(scheduleSeat -> scheduleSeatConverter.convertToDTO(scheduleSeat)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> paging(int page, int size, String search, String field, String order) {
        return null;
    }

    @Override
    public ScheduleSeatDTO save(ScheduleSeatDTO scheduleSeat) {
        ScheduleSeat scheduleSeat1 = scheduleSeatConverter.convertToEntity(scheduleSeat);
        return scheduleSeatConverter.convertToDTO(scheduleSeatRepository.save(scheduleSeat1));
    }

    @Override
    public ScheduleSeatDTO update(ScheduleSeatDTO scheduleSeat, Integer id) {
        Optional<ScheduleSeat> scheduleSeat1 = scheduleSeatRepository.findById(id);
        ScheduleSeat scheduleSeat2 = scheduleSeatConverter.convertToEntity(scheduleSeat);
        if(!scheduleSeat1.isPresent()){
            throw new NotFoundException("ScheduleSeat Not Found");
        }
        scheduleSeat1.get().setMovie(scheduleSeat2.getMovie());
        scheduleSeat1.get().setSchedule(scheduleSeat2.getSchedule());
        scheduleSeat1.get().setScheduleSeatId(scheduleSeat2.getScheduleSeatId());
        scheduleSeat1.get().setSeatColumn(scheduleSeat2.getSeatColumn());
        scheduleSeat1.get().setSeatId(scheduleSeat2.getSeatId());
        scheduleSeat1.get().setSeatRow(scheduleSeat2.getSeatRow());
        scheduleSeat1.get().setSeatType(scheduleSeat2.getSeatType());
        scheduleSeat1.get().setStatus(scheduleSeat2.getStatus());

        return scheduleSeatConverter.convertToDTO(scheduleSeatRepository.save(scheduleSeat1.get()));
    }
    @Transactional
    @Override
    public boolean delete(Integer id) {
        Optional<ScheduleSeat> scheduleSeat1 = scheduleSeatRepository.findById(id);
        if(!scheduleSeat1.isPresent()){
            throw new NotFoundException("ScheduleSeat Not Found");
        }
        try {
            scheduleSeatRepository.deleteById(id);
            return true;
        }catch (Exception e){
        e.printStackTrace();
        }
        return false;
    }

    @Override
    public ScheduleSeatDTO findById(Integer id) {
        return null;
    }
}
