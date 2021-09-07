package fa.appcode.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.dto.*;
import fa.appcode.web.service.MovieService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import fa.appcode.web.service.ShowDateMovieService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShowDateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService mockMovieService;

    @MockBean
    private ShowDateMovieService showDateMovieService;


    public List<DateMovieDTO> dateMovieDTOS = new ArrayList<>();
    public Map<String, Object> map = new HashMap<>();
    /**
     * Author: DaoTQ1
     * Description: This function testing Paging ShowDate Movie
     * Result: The test is pass. This function return ResponseEntity with status 200 and Date Movies List, totalPages,totalElement and current Page
     * @throws Exception
     */
    @Test
    void testPagingShowDateMovies() throws Exception {
        // Setup

        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO(1, "CGV01", BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), 20, null, null);
        ShowDateDTO showDateDTO = new ShowDateDTO(1, LocalDate.of(2020, 10, 10), "MON", null);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setNameVN("Chiến THần");
        movieDTO.setCinemaRoom(cinemaRoomDTO);
        movieDTO.setId(1);
        movieDTO.setVersion("1");
        movieDTO.setActor("Director Vu");
        movieDTO.setFromDate(LocalDate.of(2020, 10, 10));
        movieDTO.setDuration(200f);
        movieDTO.setToDate(LocalDate.of(2020, 10, 10));
        movieDTO.setNameEng("America Capital");
        dateMovieDTOS.add(new DateMovieDTO(showDateDTO, movieDTO));

        CinemaRoomDTO cinemaRoomDTO2 = new CinemaRoomDTO(2, "CGV01", BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), 20, null, null);
        ShowDateDTO showDateDTO2 = new ShowDateDTO(1, LocalDate.of(2020, 10, 10), "MON", null);
        MovieDTO movieDTO2 = new MovieDTO();
        movieDTO2.setCinemaRoom(cinemaRoomDTO2);
        movieDTO.setNameVN("Than Bai");
        movieDTO.setCinemaRoom(cinemaRoomDTO);
        movieDTO.setId(1);
        movieDTO.setVersion("1");
        movieDTO.setActor("Director Vu");
        movieDTO.setFromDate(LocalDate.of(2020, 11, 10));
        movieDTO.setDuration(200f);
        movieDTO.setToDate(LocalDate.of(2020, 12, 10));
        movieDTO.setNameEng("Supper Joker");
        dateMovieDTOS.add(new DateMovieDTO(showDateDTO2, movieDTO2));


        map.put("dateMovies", dateMovieDTOS);
        map.put("totalPages", 2);
        map.put("totalElements", 2);
        map.put("currentPage", 0);
        when(showDateMovieService.pagingDateMovie(1, 5, "")).thenReturn(map);
        // Run the test

        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies/showDate")
                .param("page", "1").param("size", "5").param("search", "")
                .header("Authorization", CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        String expectedResult = "{\"dateMovies\":[{\"date\":{\"id\":1,\"date\":\"2020-10-10\",\"dateName\":\"MON\",\"dateMovies\":null},\"movie\":{\"cinemaRoom\":{\"id\":1,\"roomName\":\"CGV01\",\"vipPrice\":1000,\"normalPrice\":10000,\"seatQuantity\":20,\"movies\":null,\"seats\":null},\"id\":1,\"actor\":\"Director Vu\",\"content\":null,\"director\":null,\"duration\":200.0,\"fromDate\":\"2020-11-10\",\"toDate\":\"2020-12-10\",\"movieCompany\":null,\"version\":\"1\",\"nameEng\":\"Supper Joker\",\"nameVN\":\"Than Bai\",\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}},{\"date\":{\"id\":1,\"date\":\"2020-10-10\",\"dateName\":\"MON\",\"dateMovies\":null},\"movie\":{\"cinemaRoom\":{\"id\":2,\"roomName\":\"CGV01\",\"vipPrice\":1000,\"normalPrice\":10000,\"seatQuantity\":20,\"movies\":null,\"seats\":null},\"id\":null,\"actor\":null,\"content\":null,\"director\":null,\"duration\":null,\"fromDate\":null,\"toDate\":null,\"movieCompany\":null,\"version\":null,\"nameEng\":null,\"nameVN\":null,\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}}],\"totalPages\":2,\"currentPage\":0,\"totalElements\":2}";
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResult, response.getContentAsString());
    }
    /**
     * Author: DaoTQ1
     * Description: This function testing Paging ShowDate Movie when DateMovie List is empty
     * Result: The test is pass. This function return ResponseEntity with status 200 and empty List Invoice
     * @throws Exception
     */
    @org.junit.jupiter.api.Test
    void testPagingMovieReturnNoItem() throws Exception {
        // Setup

        map.put("dateMovies", null);
        map.put("totalPages", 0);
        map.put("totalElements", 0);
        map.put("currentPage", 1);
        when(showDateMovieService.pagingDateMovie(1, 5, "")).thenReturn(map);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies/showDate")
                .param("page", "1").param("size", "5").param("search", "")
                .header("Authorization", CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        String expectedResult = "{\"dateMovies\":null,\"totalPages\":0,\"currentPage\":1,\"totalElements\":0}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResult, response.getContentAsString());

    }
    /**
     * Author: DaoTQ1
     * Description: This function testing Paging ShowDate Movie when search date = "MON"
     * Result: The test is pass. This function return ResponseEntity with status 200 and List of Movie has release date with Monday
     * @throws Exception
     */
    @org.junit.jupiter.api.Test
    void testPagingMovieSearchByDay() throws Exception {
        // Setup
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO(1, "CGV01", BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), 20, null, null);
        ShowDateDTO showDateDTO = new ShowDateDTO(1, LocalDate.of(2020, 10, 10), "MON", null);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setNameVN("Chiến THần");
        movieDTO.setCinemaRoom(cinemaRoomDTO);
        movieDTO.setId(1);
        movieDTO.setVersion("1");
        movieDTO.setActor("Director Vu");
        movieDTO.setFromDate(LocalDate.of(2020, 10, 10));
        movieDTO.setDuration(200f);
        movieDTO.setToDate(LocalDate.of(2020, 10, 10));
        movieDTO.setNameEng("America Capital");
        dateMovieDTOS.add(new DateMovieDTO(showDateDTO, movieDTO));




        map.put("dateMovies", dateMovieDTOS);
        map.put("totalPages", 1);
        map.put("totalElements", 1);
        map.put("currentPage", 1);
        when(showDateMovieService.pagingDateMovie(1, 5, "MON")).thenReturn(map);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies/showDate")
                .param("page", "1").param("size", "5").param("search", "MON")
                .header("Authorization", CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        String expectedResult ="{\"dateMovies\":[{\"date\":{\"id\":1,\"date\":\"2020-10-10\",\"dateName\":\"MON\",\"dateMovies\":null},\"movie\":{\"cinemaRoom\":{\"id\":1,\"roomName\":\"CGV01\",\"vipPrice\":1000,\"normalPrice\":10000,\"seatQuantity\":20,\"movies\":null,\"seats\":null},\"id\":1,\"actor\":\"Director Vu\",\"content\":null,\"director\":null,\"duration\":200.0,\"fromDate\":\"2020-10-10\",\"toDate\":\"2020-10-10\",\"movieCompany\":null,\"version\":\"1\",\"nameEng\":\"America Capital\",\"nameVN\":\"Chiáº¿n THáº§n\",\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}}],\"totalPages\":1,\"currentPage\":1,\"totalElements\":1}";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResult, response.getContentAsString());

    }

    /**
     * Author: DaoTQ1
     * Description: This function testing Paging ShowDate Movie when search date = "MON"
     * Result: The test is pass. This function return ResponseEntity with status 200 and List empty when any record matches with date release is MONDAY
     * @throws Exception
     */
    @org.junit.jupiter.api.Test
    public void testSearchMovieEmptyResult() throws Exception {
        // Setup

        when(mockMovieService.searchAllMovie("MON")).thenReturn(Collections.EMPTY_LIST);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies")
                .param("search", "MON")
                .header("Authorization", CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }
}
