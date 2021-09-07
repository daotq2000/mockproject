package fa.appcode.web.controller;

import fa.appcode.web.commons.exception.DateRangeException;
import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.exception.TokenExpiredException;
import fa.appcode.web.commons.utility.HTTP;
import fa.appcode.web.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@RestControllerAdvice
public class ExceptionController {
    ExceptionDTO exceptionDTO =null;
    @ExceptionHandler
    private ResponseEntity handleNotFoundException(NotFoundException e, HttpServletRequest request) {
         exceptionDTO= new ExceptionDTO();
        exceptionDTO.setStatus(HTTP.NOT_FOUND);
        exceptionDTO.setTimestamp(new Date());
        exceptionDTO.setPath(request.getRequestURL().toString());
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus(HTTP.BAD_REQUEST);
        exceptionDTO.setTimestamp(new Date());
        exceptionDTO.setPath(request.getRequestURL().toString());
        exceptionDTO.setErrors(e.getAllErrors());
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity handleDateRangeException(DateRangeException e, HttpServletRequest request) {
        exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus(HTTP.BAD_REQUEST);
        exceptionDTO.setTimestamp(new Date());
        exceptionDTO.setPath(request.getRequestURL().toString());
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity handleTokenExpiredException(TokenExpiredException e, HttpServletRequest request) {
        exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus(HTTP.NON_AUTHORIZE);
        exceptionDTO.setTimestamp(new Date());
        exceptionDTO.setPath(request.getRequestURL().toString());
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity handleIllegalArgumentExceptionException(IllegalArgumentException e, HttpServletRequest request) {
        exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus(HTTP.NON_AUTHORIZE);
        exceptionDTO.setTimestamp(new Date());
        exceptionDTO.setPath(request.getRequestURL().toString());
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
