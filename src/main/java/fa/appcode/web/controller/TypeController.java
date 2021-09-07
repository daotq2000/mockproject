package fa.appcode.web.controller;

import fa.appcode.web.dto.TypeDTO;
import fa.appcode.web.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public ResponseEntity<List<TypeDTO>> getAll(){
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }
}
