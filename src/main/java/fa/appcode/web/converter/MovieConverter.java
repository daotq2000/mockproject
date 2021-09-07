package fa.appcode.web.converter;

import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.dto.ScheduleMovieDTO;
import fa.appcode.web.dto.TypeMovieDTO;
import fa.appcode.web.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fa.appcode.web.converter.TypeConverter;
import java.util.ArrayList;
import java.util.List;
@Component
public class MovieConverter {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired private ScheduleConverter scheduleConverter;
    @Autowired private ScheduleMovieConverter scheduleMovieConverter;
    @Autowired private TypeConverter typeConverter;
    @Autowired private TypeMovieConverter typeMovieConverter;
    @Autowired private CinemaRoomConverter cinemaRoomConverter;

    public MovieDTO convertToDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setActor(movie.getActor());
        movieDTO.setAvatar(movie.getAvatar());
        movieDTO.setContent(movie.getContent());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setFromDate(movie.getFromDate());
        movieDTO.setToDate(movie.getToDate());
        movieDTO.setImage(movie.getImage());
        movieDTO.setId(movie.getId());
        movieDTO.setMovieCompany(movie.getMovieCompany());
        movieDTO.setNameEng(movie.getNameEng());
        movieDTO.setNameVN(movie.getNameVN());
        movieDTO.setVersion(movie.getVersion());
        if (movie.getCinemaRoom()!=null){
            movieDTO.setCinemaRoom(cinemaRoomConverter.convertToDTO(movie.getCinemaRoom()));
        }
        List<ScheduleMovieDTO> scheduleMovieDTOS = new ArrayList<>();
        if(movie.getScheduleMovies() != null){
            movie.getScheduleMovies().forEach(scheduleMovie -> {
                scheduleMovieDTOS.add(scheduleMovieConverter.convertToDTO(scheduleMovie));
            });
        }
        movieDTO.setScheduleMovies(scheduleMovieDTOS);
        List<TypeMovieDTO> typeMovieDTOS = new ArrayList<>();
        if(movie.getTypeMovies() != null){
            movie.getTypeMovies().forEach(typeMovie -> {
                typeMovieDTOS.add(typeMovieConverter.convertToDTO(typeMovie));
            });
        }
        movieDTO.setTypeMovies(typeMovieDTOS);
        return movieDTO;
    }
    public Movie convertToEntity(MovieDTO movieDTO){

        Movie movie = new Movie();
        movie.setActor(movieDTO.getActor());
        movie.setAvatar(movieDTO.getAvatar());
        movie.setContent(movieDTO.getContent());
        movie.setDirector(movieDTO.getDirector());
        movie.setDuration(movieDTO.getDuration());
        movie.setId(movieDTO.getId());
        movie.setVersion(movieDTO.getVersion());
        movie.setFromDate(movieDTO.getFromDate());
        movie.setToDate(movieDTO.getToDate());
        movie.setNameVN(movieDTO.getNameVN());
        movie.setNameEng(movieDTO.getNameEng());
        movie.setMovieCompany(movieDTO.getMovieCompany());
        movie.setImage(movieDTO.getImage());

        List<ScheduleMovie> scheduleMovies = new ArrayList<>();
        if(movieDTO.getScheduleMovies() != null){
            movieDTO.getScheduleMovies().forEach(scheduleMovieDTO -> {
                ScheduleMovieId scheduleMovieId = new ScheduleMovieId();
                scheduleMovieId.setSchedule(scheduleConverter.convertToEntity(scheduleMovieDTO.getSchedule()));
                scheduleMovieId.setMovie(movie);
                ScheduleMovie scheduleMovie = new ScheduleMovie();
                scheduleMovie.setScheduleMovieId(scheduleMovieId);
                scheduleMovies.add(scheduleMovie);
            });
            movie.setScheduleMovies(scheduleMovies);

        }
        if(movieDTO.getCinemaRoom() != null){
            movie.setCinemaRoom(cinemaRoomConverter.convertToEntity(movieDTO.getCinemaRoom()));
        }
        //type
        if(movieDTO.getTypeMovies() != null){
            List<TypeMovie> typeMovies = new ArrayList<>();
            movieDTO.getTypeMovies().forEach(typeMovieDTO -> {
                TypeMovieId typeMovieId = new TypeMovieId();
                typeMovieId.setType(typeConverter.convertToEntity(typeMovieDTO.getType()));
                typeMovieId.setMovie(movie);
                TypeMovie typeMovie = new TypeMovie();
                typeMovie.setTypeMovieId(typeMovieId);
                typeMovies.add(typeMovie);
            });
            movie.setTypeMovies(typeMovies);
        }

        return movie;
    }
}

