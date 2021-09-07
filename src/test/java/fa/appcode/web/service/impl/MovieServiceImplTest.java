package fa.appcode.web.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.MovieConverter;
import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.entities.Movie;
import fa.appcode.web.repository.MovieRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

  @Mock
  private MovieRepository mockMovieRepository;
  @Mock
  private MovieConverter mockMovieConverter;

  private MovieServiceImpl movieServiceImplUnderTest;

  @Before
  public void setUp() {
    movieServiceImplUnderTest =
        new MovieServiceImpl(mockMovieRepository, mockMovieConverter);
  }

//  @Test
//  public void testSave_OK() {
//    // Setup
//    final MovieDTO movieDto =
//        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), "Warner Bros",
//            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    // Configure MovieConverter.convertToEntity(...).
//    final Movie movie = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 5),
//        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieConverter.convertToEntity(any(MovieDTO.class)))
//        .thenReturn(movie);
//
//    // Configure MovieConverter.convertToDTO(...).
//    final MovieDTO movieDTO =
//        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), "Warner Bros",
//            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieConverter.convertToDTO(any(Movie.class)))
//        .thenReturn(movieDTO);
//
//    // Configure MovieRepository.save(...).
//    final Movie movie1 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 5),
//        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);
//
//    // Run the test
//    final MovieDTO result = movieServiceImplUnderTest.save(movieDto);
//
//    // Verify the results
//    assertEquals(result,movieDto);
//  }


  @Test
  public void testSave_null() {
    // Setup
    final MovieDTO movieDto =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    // Configure MovieConverter.convertToEntity(...).
    final Movie movie = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToEntity(any(MovieDTO.class)))
        .thenReturn(movie);

    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

    // Configure MovieRepository.save(...).
    final Movie movie1 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);

    Exception exception = assertThrows(RuntimeException.class, ()->movieServiceImplUnderTest.save(null));
    String expectedMsg = "can't save movie";
    String actualMsg = exception.getMessage();
    assertTrue(actualMsg.contains(expectedMsg));
  }

//  @Test
//  public void testUpdate_success() {
//    // Setup
//    final MovieDTO movieDto =
//        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), "Warner Bros",
//            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//
//    // Configure MovieRepository.findById(...).
//    final Optional<Movie> movie = Optional
//        .of(new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//            LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 5),
//            "version", "Justice league", "Lien minh cong ly", "avatar", "image"));
//    when(mockMovieRepository.findById(0)).thenReturn(movie);
//
//    // Configure MovieConverter.convertToEntity(...).
//    final Movie movie1 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 5),
//        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieConverter.convertToEntity(any(MovieDTO.class)))
//        .thenReturn(movie1);
//
//    // Configure MovieConverter.convertToDTO(...).
//    final MovieDTO movieDTO =
//        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), "Warner Bros",
//            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieConverter.convertToDTO(any(Movie.class)))
//        .thenReturn(movieDTO);
//
//    // Configure MovieRepository.save(...).
//    final Movie movie2 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
//        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 5),
//        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
//    when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie2);
//
//    // Run the test
//    final MovieDTO result = movieServiceImplUnderTest.update(movieDto, 0);
//
//    // Verify the results
//    assertEquals(result,movieDto);
//  }



  @Test
  public void testUpdate_MovieNotFound() {
    // Setup
    final MovieDTO movieDto =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieRepository.findById(0)).thenReturn(Optional.empty());
    // Configure MovieConverter.convertToEntity(...).
    final Movie movie = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToEntity(any(MovieDTO.class)))
        .thenReturn(movie);
    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);
    // Configure MovieRepository.save(...).
    final Movie movie1 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);
    // Run the test
    Exception exception = assertThrows(NotFoundException.class, ()->movieServiceImplUnderTest.update(movieDto, 0));
    String expectedMsg = "movie Not Found";
    String actualMsg = exception.getMessage();
    assertTrue(actualMsg.contains(expectedMsg));
  }

  @Test
  public void testUpdate_null_input() {
    // Setup
    final MovieDTO movieDto =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieRepository.findById(0)).thenReturn(Optional.empty());
    // Configure MovieConverter.convertToEntity(...).
    final Movie movie = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToEntity(any(MovieDTO.class)))
        .thenReturn(movie);
    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);
    // Configure MovieRepository.save(...).
    final Movie movie1 = new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
        LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
        "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);
    // Run the test
    Exception exception = assertThrows(NotFoundException.class, ()->movieServiceImplUnderTest.update(null, 0));
    String expectedMsg = "movie Not Found";
    String actualMsg = exception.getMessage();
    assertTrue(actualMsg.contains(expectedMsg));
  }


  @Test
  public void testdelete_success() {
    // Setup
    // Run the test
    final boolean result = movieServiceImplUnderTest.delete(0);
    // Verify the results
    assertTrue(result);
    verify(mockMovieRepository).deleteById(0);
  }
  @Test
  public void testdelete_fail() {
    // Setup
    doThrow(new RuntimeException()).when(mockMovieRepository).deleteById(0);
    Exception exception = assertThrows(RuntimeException.class, ()->movieServiceImplUnderTest.delete( 0));
    String expectedMsg = "Did not delete movie id";
    String actualMsg = exception.getMessage();
    assertTrue(actualMsg.contains(expectedMsg));
  }

  @Test
  public void testFindById_found() {
    // Setup

    // Configure MovieRepository.findById(...).
    final Optional<Movie> movie = Optional
        .of(new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
            "version", "Justice league", "Lien minh cong ly", "avatar", "image"));
    when(mockMovieRepository.findById(0)).thenReturn(movie);

    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

    // Run the test
    final MovieDTO result = movieServiceImplUnderTest.findById(0);

    // Verify the results
  }

  @Test
  public void testFindById_Notfound() {
    // Setup
    when(mockMovieRepository.findById(0)).thenReturn(Optional.empty());
    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

// Run the test
    Exception exception = assertThrows(NotFoundException.class, ()->movieServiceImplUnderTest.findById(0));
    String expectedMsg = "Did not find movie id";
    String actualMsg = exception.getMessage();
    assertTrue(actualMsg.contains(expectedMsg));
  }
  @Test
  public void testFindAll_morethanonerecord() {
    // Setup
    // Configure MovieRepository.findAll(...).
    final List<Movie> movies = Arrays.asList(
        new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
            "version", "Justice league", "Lien minh cong ly", "avatar", "image"),
        new Movie(1, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
            "version", "Justice league", "Lien minh cong ly", "avatar", "image"));
    when(mockMovieRepository.findAll()).thenReturn(movies);

    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

    // Run the test
    final List<MovieDTO> result = movieServiceImplUnderTest.findAll();

    // Verify the results
    assertTrue(result.size()==2);
  }


  @Test
  public void testFindAll_onerecord() {
    // Setup

    // Configure MovieRepository.findAll(...).
    final List<Movie> movies = Arrays.asList(
        new Movie(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), "Warner Bros", LocalDate.of(2020, 1, 1),
            "version", "Justice league", "Lien minh cong ly", "avatar", "image"));
    when(mockMovieRepository.findAll()).thenReturn(movies);

    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

    // Run the test
    final List<MovieDTO> result = movieServiceImplUnderTest.findAll();

    // Verify the results
    assertNotNull(result.size());
  }

  @Test
  public void testFindAll_norecord() {
    // Setup
    when(mockMovieRepository.findAll()).thenReturn(Collections.emptyList());

    // Configure MovieConverter.convertToDTO(...).
    final MovieDTO movieDTO =
        new MovieDTO(0, "adam sandler", "Justice league", "Zack snyder", 0.0f,
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "Warner Bros",
            "version", "Justice league", "Lien minh cong ly", "avatar", "image");
    when(mockMovieConverter.convertToDTO(any(Movie.class)))
        .thenReturn(movieDTO);

    // Run the test
    final List<MovieDTO> result = movieServiceImplUnderTest.findAll();

    // Verify the results
    assertEquals(0,result.size());
  }
}
