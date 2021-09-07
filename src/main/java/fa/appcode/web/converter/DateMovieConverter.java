package fa.appcode.web.converter;

import fa.appcode.web.dto.DateMovieDTO;
import fa.appcode.web.entities.DateMovie;
import fa.appcode.web.entities.DateMovieId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateMovieConverter {
    @Autowired private ShowDateConverter showDateConverter;
    @Autowired private MovieConverter movieConverter;
    public DateMovieDTO convertToDTO(DateMovie dateMovie){
        DateMovieDTO dateMovieDTO = new DateMovieDTO();
        dateMovieDTO.setDate(showDateConverter.convertToDTO(dateMovie.getDateMovieId().getDate()));
        dateMovieDTO.setMovie(movieConverter.convertToDTO(dateMovie.getDateMovieId().getMovie()));
        return dateMovieDTO;
    }
    public DateMovie dateMovie(DateMovieDTO dateMovieDTO){
        DateMovie dateMovie = new DateMovie();
        DateMovieId dateMovieId = new DateMovieId();
        dateMovieId.setDate(showDateConverter.convertToEntity(dateMovieDTO.getDate()));
        dateMovieId.setMovie(movieConverter.convertToEntity(dateMovieDTO.getMovie()));
        return dateMovie;
    }
}
