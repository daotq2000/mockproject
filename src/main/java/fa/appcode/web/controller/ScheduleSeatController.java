package fa.appcode.web.controller;

import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.dto.ScheduleSeatDTO;
import fa.appcode.web.entities.ScheduleSeat;
import fa.appcode.web.service.ScheduleSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/scheduleSeat")
public class ScheduleSeatController {
    @Autowired
    private ScheduleSeatService scheduleSeatService;
    @GetMapping("/checkBooking/{date}/{movieId}/{scheduleId}")
    public ResponseEntity<List<ScheduleSeatDTO>> checkBookingSeat(@PathVariable("date")String date,
                                                                  @PathVariable("movieId") int movieId,
                                                                  @PathVariable("scheduleId") int scheduleId){
        LocalDate localDate =LocalDate.parse(date);
        return new ResponseEntity<>(scheduleSeatService.getAllSeatBooking(localDate, movieId, scheduleId),HttpStatus.OK);
    }
}
