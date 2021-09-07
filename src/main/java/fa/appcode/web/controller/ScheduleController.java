package fa.appcode.web.controller;

import fa.appcode.web.dto.ScheduleDTO;
import fa.appcode.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleDTO>> getAll(){
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }
}
