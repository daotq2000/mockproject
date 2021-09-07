package fa.appcode.web.controller;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.service.MovieService;
import fa.appcode.web.service.ShowDateMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {
  private MovieService movieService;
  private ShowDateMovieService showDateMovieService;

  @Autowired
  public MovieController(MovieService movieService,ShowDateMovieService showDateMovieService) {
    this.movieService = movieService;
    this.showDateMovieService = showDateMovieService;
  }

  /**
   * Api to get movie date.
   * @param page   page number
   * @param size   number of record per one page
   * @param search search string to search date movie
   * @return list of datemovie fit the condition
   */
  @GetMapping("/movies/showDate")
  public ResponseEntity<Map<String, Object>> pagingShowDateMovies(
      @RequestParam(value = "page", required = false, defaultValue = "1")
          int page,
      @RequestParam(value = "size", required = false, defaultValue = "10")
          int size,
      @RequestParam(value = "search", required = false, defaultValue = "")
          String search) {
    return new ResponseEntity<>(
        showDateMovieService.pagingDateMovie(page, size, search),
        HttpStatus.OK);
  }

  /**
   * api to get movie by page.
   * @author kiendo
   * @param search search string to search by english name or vietnamese name
   * @return list of movie has fit the condition
   */
  @GetMapping("/movie")
  public ResponseEntity<List<MovieDTO>> pagingMovie(
      @RequestParam(value = "search", required = false, defaultValue = "")
          String search) {
    return new ResponseEntity<>(movieService.searchAllMovie(search),
        HttpStatus.OK);
  }

  /**
   * api to get all movie.
   * @author kiendo
   * @return List of movies
   */
  @GetMapping("/movies")
  public ResponseEntity<List<MovieDTO>> findAll() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    System.out.println(authentication);
    List<MovieDTO> movies = movieService.findAll();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  /**
   * api to get movie by id.
   * @author kiendo
   * @param movieId movie id of movie want to get
   * @return movie information of movie has id= movieId
   */
  @GetMapping("/movies/{movieId}")
  public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer movieId) throws ParseException {
    MovieDTO movie = movieService.findById(movieId);
    if (movie == null) {
      throw new NotFoundException(" movie id not found - " + movieId);
    }
    return new ResponseEntity<>(movie, HttpStatus.OK);
  }

  /**
   * api to add a new movie.
   * @author kiendo
   * @param movieDto movie information to add
   * @return movie information after add movie
   */
  @PostMapping("/movies")
  public ResponseEntity<MovieDTO> addMovie(
      @Valid @RequestBody MovieDTO movieDto) throws ParseException {
    MovieDTO movieDto1 = movieService.save(movieDto);
    return new ResponseEntity<>(movieDto1, HttpStatus.OK);
  }

  /**
   * api to update a exist movie.
   * @author kiendo
   * @param id id of movie want to update
   * @param movieDto movie info to update
   * @return json of movie info has deleted
   */
  @PutMapping("/movies/{id}")
  public ResponseEntity<MovieDTO> updateMovie(@PathVariable("id") Integer id,
                                              @Valid @RequestBody
                                                  MovieDTO movieDto) {
    MovieDTO movieDto1 = movieService.update(movieDto, id);

    return new ResponseEntity<>(movieDto1, HttpStatus.ACCEPTED);
  }

  /**
   * api to delete a movie.
   * @author kiendo
   * @param movieId movie id want to delete
   * @return string to show if delete success or not
   */
  @DeleteMapping("/movies/{movieId}")
  public String deleteMovie(@PathVariable Integer movieId) throws ParseException {

    MovieDTO movie = movieService.findById(movieId);

    // throw exception if null

    if (movie == null) {
      throw new NotFoundException("Movie id not found - " + movieId);
    }

    movieService.delete(movieId);

    return "Deleted Customer id - " + movieId;
  }

}
