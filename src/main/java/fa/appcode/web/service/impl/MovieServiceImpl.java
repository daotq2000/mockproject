package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.MovieConverter;
import fa.appcode.web.converter.ScheduleConverter;
import fa.appcode.web.converter.ScheduleMovieConverter;
import fa.appcode.web.converter.TypeConverter;
import fa.appcode.web.converter.TypeMovieConverter;
import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.entities.DateMovie;
import fa.appcode.web.entities.DateMovieId;
import fa.appcode.web.entities.Movie;
import fa.appcode.web.entities.ScheduleMovie;
import fa.appcode.web.entities.ScheduleMovieId;
import fa.appcode.web.entities.ShowDate;
import fa.appcode.web.entities.TypeMovie;
import fa.appcode.web.entities.TypeMovieId;
import fa.appcode.web.repository.DateMovieRepository;
import fa.appcode.web.repository.MovieRepository;
import fa.appcode.web.repository.ScheduleMovieRepository;
import fa.appcode.web.repository.ShowDateMovieRepository;
import fa.appcode.web.repository.ShowDateRepository;
import fa.appcode.web.repository.TypeMovieRepository;
import fa.appcode.web.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
  private MovieRepository movieRepository;
  private MovieConverter movieConverter;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository,
                          MovieConverter movieConverter) {
    this.movieRepository = movieRepository;
    this.movieConverter = movieConverter;
  }

  @Autowired
  private ScheduleMovieConverter scheduleMovieConverter;
  @Autowired
  private ScheduleConverter schedule;
  @Autowired
  private TypeConverter typeConverter;
  @Autowired
  private TypeMovieConverter typeMovieConverter;
  @Autowired
  private DateMovieRepository dateMovieRepository;
  @Autowired
  private ShowDateMovieRepository showDateMovieRepository;
  @Autowired
  private ScheduleMovieRepository scheduleMovieRepository;
  @Autowired
  private TypeMovieRepository typeMovieRepository;
  @Autowired
  private ShowDateRepository showDateRepository;


  @Override
  public Map<String, Object> paging(int page, int size, String search,
                                    String field, String order) {
    return null;
  }

  /**
   * method to save a new movie.
   *
   * @param movieDto movie info to add
   * @return movie info after add movie
   * @author kiendo
   */
  @Override
  public MovieDTO save(MovieDTO movieDto) {
    try {
      Movie movie = movieConverter.convertToEntity(movieDto);
      List<ScheduleMovie> scheduleMovies = new ArrayList<>();
      if (movieDto.getScheduleMovies() != null) {
        movieDto.getScheduleMovies().forEach(scheduleMovieDTO -> {
          ScheduleMovieId scheduleMovieId = new ScheduleMovieId();
          ScheduleMovie scheduleMovie = new ScheduleMovie();
          scheduleMovieId.setMovie(movie);
          scheduleMovieId.setSchedule(scheduleMovieConverter
                  .convertToEntity(scheduleMovieDTO.getSchedule()));
          scheduleMovie.setScheduleMovieId(scheduleMovieId);
          scheduleMovies.add(scheduleMovie);
        });
      }
      movie.setScheduleMovies(scheduleMovies);
      List<TypeMovie> typeMovies = new ArrayList<>();
      if (movieDto.getTypeMovies() != null) {
        movieDto.getTypeMovies().forEach(typeMovieDTO -> {
          TypeMovieId typeMovieId = new TypeMovieId();
          TypeMovie typeMovie = new TypeMovie();
          typeMovieId.setMovie(movie);
          typeMovieId.setType(
                  typeMovieConverter.convertToEntity(typeMovieDTO.getType()));
          typeMovie.setTypeMovieId(typeMovieId);
          typeMovies.add(typeMovie);
        });
      }
      movie.setTypeMovies(typeMovies);
      movie.setDateMovies(saveDate(movie));
      return movieConverter.convertToDTO(movieRepository.save(movie));
    } catch (Exception e) {
      throw new RuntimeException("can't save movie");
    }
  }

  /**
   * method to update movie information.
   *
   * @param movieDto movie info to update pass from client side
   * @param id       id of movie want to update
   * @return movie update information
   * @author kiendo
   */
  @Override
  public MovieDTO update(MovieDTO movieDto, Integer id) {
    //movie1: movie muon update
    //movie2: thong tin update
    Optional<Movie> movie1 = movieRepository.findById(id);
    Movie movie2 = movieConverter.convertToEntity(movieDto);
    if (!movie1.isPresent()) {
      throw new NotFoundException("movie Not Found");
    }
    List<ScheduleMovie> scheduleMovies = new ArrayList<>();
    if (null != movie2.getScheduleMovies()) {
      movie2.getScheduleMovies().forEach(scheduleMovie -> {
        ScheduleMovieId scheduleMovieId = new ScheduleMovieId();
        scheduleMovieId.setMovie(movie1.get());
        scheduleMovieId
                .setSchedule(scheduleMovie.getScheduleMovieId().getSchedule());
        scheduleMovies.add(new ScheduleMovie(scheduleMovieId));
      });
    }
    List<TypeMovie> typeMovies = new ArrayList<>();
    if (null != movie2.getTypeMovies()) {
      movie2.getTypeMovies().forEach(typeMovie -> {
        TypeMovieId typeMovieId = new TypeMovieId();
        typeMovieId.setMovie(movie1.get());
        typeMovieId.setType(typeMovie.getTypeMovieId().getType());
        typeMovies.add(new TypeMovie(typeMovieId));

      });
    }
    if (null != movie1.get().getId() && null != movie2.getTypeMovies() && null != movie2.getScheduleMovies()) {
      //delete data from type_movie and schedule_movie table
      typeMovieRepository.deleteType(movie1.get().getId());
      scheduleMovieRepository.deleteSchedule(movie1.get().getId());
      dateMovieRepository.deleteDate(movie1.get().getId());
    }


    movie1.get().setId(movie2.getId());
    movie1.get().setImage(movie2.getImage());
    movie1.get().setMovieCompany(movie2.getMovieCompany());
    movie1.get().setNameEng(movie2.getNameEng());
    movie1.get().setNameVN(movie2.getNameVN());
    movie1.get().setToDate(movie2.getToDate());
    movie1.get().setFromDate(movie2.getFromDate());
    movie1.get().setVersion(movie2.getVersion());
    movie1.get().setDuration(movie2.getDuration());
    movie1.get().setDirector(movie2.getDirector());
    movie1.get().setContent(movie2.getContent());
    movie1.get().setAvatar(movie2.getAvatar());
    movie1.get().setActor(movie2.getActor());
    movie1.get().setCinemaRoom(movie2.getCinemaRoom());
    movie1.get().setScheduleMovies(scheduleMovies);
    movie1.get().setTypeMovies(typeMovies);
    movie1.get().setDateMovies(saveDate(movie2));
    return movieConverter.convertToDTO(movieRepository.save(movie1.get()));
  }

  /**
   * api to delete movie by id.
   *
   * @param id id of movie want to delete
   * @return true if delete success, false if delete fail
   * @author kiendo
   */
  @Override
  public boolean delete(Integer id) {
    try {
      movieRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new RuntimeException("Did not delete movie id - " + id);
    }
  }

  /**
   * api to find a movie by id.
   *
   * @param id id of a movie want to find
   * @return movie found, if cannot find movie, return RuntimeException
   * @author kiendo
   */
  @Override
  public MovieDTO findById(Integer id) {
    Optional<Movie> result = movieRepository.findById(id);

    Movie movie = null;

    if (result.isPresent()) {
      movie = result.get();
    } else {
      // we didn't find the movie
      throw new NotFoundException("Did not find movie id - " + id);
    }

    return movieConverter.convertToDTO(movie);
  }

  /**
   * api to search movie.
   *
   * @param search string to search by name english or name VN
   * @return movie has found
   * @author daotran
   */
  @Override
  public List<MovieDTO> searchAllMovie(String search) {
    List<Movie> movies = movieRepository.searchAllMovie(search);
    List<MovieDTO> movieDtos =
            movies.stream().map(movie -> movieConverter.convertToDTO(movie))
                    .collect(Collectors.toList());
    return movieDtos;
  }

  /**
   * method to get all movie.
   *
   * @return list of movies
   * @author kiendo
   */
  @Override
  public List<MovieDTO> findAll() {
    List<Movie> movies = movieRepository.findAll();
    return movies.stream().map(movie -> movieConverter.convertToDTO(movie))
            .collect(Collectors.toList());
  }

  /**
   * method to save date information to date table and datemovie table.
   *
   * @param movie save date movie of a movie,
   *              1. get dates between startdate and end date
   *              2. loop dates in step 1, if date already exist from date table,
   *              we just save date to moviedate,
   *              if date doesn't exist in date table, add date to date table
   *              and datemovie table
   * @return list of datemovie to save to movie
   * @author kiendo
   */
  private List<DateMovie> saveDate(Movie movie) {
    //get list of date beetween 2 date
    LocalDate start = movie.getFromDate();
    LocalDate end = movie.getToDate();
    List<LocalDate> totalDates = new ArrayList<>();
    while (!start.isAfter(end)) {
      totalDates.add(start);
      start = start.plusDays(1);
    }
    List<DateMovie> dateMovies = new ArrayList<>();
    if (totalDates.size() > 0) {
      for (int i = 0; i < totalDates.size(); i++) {
        ShowDate showDate = new ShowDate();
        if (null != totalDates.get(i) && !showDateRepository.existsByDate(totalDates.get(i))) {
          System.out.println("null");

          showDate.setDate(totalDates.get(i));
          showDate.setDateName(
                  totalDates.get(i).getDayOfWeek().toString().substring(0, 3));
          ShowDate showDateSave = showDateRepository.save(showDate);
          DateMovieId dateMovieId = new DateMovieId();
          dateMovieId.setDate(showDateSave);
          DateMovie dateMovie = new DateMovie(dateMovieId);
          dateMovies.add(dateMovie);
        } else {
          showDate.setDate(totalDates.get(i));
          ShowDate showDateSave =
                  showDateRepository.findByDate(showDate.getDate());
          DateMovieId dateMovieId = new DateMovieId();
          dateMovieId.setDate(showDateSave);
          DateMovie dateMovie = new DateMovie(dateMovieId);
          dateMovies.add(dateMovie);
        }
      }
      dateMovies.forEach(dateMovie -> {
        DateMovieId dateMovieId = dateMovie.getDateMovieId();
        dateMovieId.setMovie(movie);
      });
      return dateMovies;
    } else {
      throw new NullPointerException();
    }

  }
}