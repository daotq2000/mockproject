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
import org.junit.Test;
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
public class MovieControllerTest {

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
    public void testFindAll_multiRecord() throws Exception {
        // Setup

        // Configure MovieService.findAll(...).
        final List<MovieDTO> movieDTOS = Arrays.asList(
                new MovieDTO(0, "Kiendo", "justice league", "zack snyder", 0.0f,
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany",
                        "version", "justice league", "nameVN", "avatar", "image"),
                new MovieDTO(1, "actor", "content", "director", 0.0f,
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany",
                        "version", "nameEng", "nameVN", "avatar", "image")
        );
        when(mockMovieService.findAll()).thenReturn(movieDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // Verify the results
        String jsonExpected =
                "[{\"cinemaRoom\":null,\"id\":0,\"actor\":\"Kiendo\",\"content\":\"justice league\",\"director\":\"zack snyder\",\"duration\":0.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"movieCompany\",\"version\":\"version\",\"nameEng\":\"justice league\",\"nameVN\":\"nameVN\",\"avatar\":\"avatar\",\"image\":\"image\",\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null},{\"cinemaRoom\":null,\"id\":1,\"actor\":\"actor\",\"content\":\"content\",\"director\":\"director\",\"duration\":0.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"movieCompany\",\"version\":\"version\",\"nameEng\":\"nameEng\",\"nameVN\":\"nameVN\",\"avatar\":\"avatar\",\"image\":\"image\",\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonExpected, response.getContentAsString());
    }



    @Test
    public void testFindAll_oneRecord() throws Exception {
        // Setup
        // Configure MovieService.findAll(...).
        final List<MovieDTO> movieDTOS = Arrays.asList(
                new MovieDTO(0, "adam sandler", "content", "kiendo", 0.0f,
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany",
                        "version", "nameEng", "nameVN", "avatar", "image"));
        when(mockMovieService.findAll()).thenReturn(movieDTOS);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // Verify the results
        String jsonExpected =
                "[{\"cinemaRoom\":null,\"id\":0,\"actor\":\"adam sandler\",\"content\":\"content\",\"director\":\"kiendo\",\"duration\":0.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"movieCompany\",\"version\":\"version\",\"nameEng\":\"nameEng\",\"nameVN\":\"nameVN\",\"avatar\":\"avatar\",\"image\":\"image\",\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}]";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonExpected, response.getContentAsString());
    }

    @Test
    public void testFindAll_noRecord() throws Exception {
        // Setup
        when(mockMovieService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/movies")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    public void testGetMovie_found() throws Exception {
        // Setup

        // Configure MovieService.findById(...).
        final MovieDTO movieDTO =
                new MovieDTO(0, "kiendo", "content", "kiendo", 0.0f,
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany",
                        "version", "nameEng", "nameVN", "avatar", "image");
        when(mockMovieService.findById(0)).thenReturn(movieDTO);

        // Run the test
        final MockHttpServletResponse response =
                mockMvc.perform(get("/api/movies/{movieId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        String expected =
                "{\"cinemaRoom\":null,\"id\":0,\"actor\":\"kiendo\",\"content\":\"content\",\"director\":\"kiendo\",\"duration\":0.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"movieCompany\",\"version\":\"version\",\"nameEng\":\"nameEng\",\"nameVN\":\"nameVN\",\"avatar\":\"avatar\",\"image\":\"image\",\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}";
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }
    @Test
    public void testGetMovie_notFound() throws Exception {
        // Setup

        when(mockMovieService.findById(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response =
                mockMvc.perform(get("/api/movies/{movieId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void testAddMovie_badrequest() throws Exception {
        // Setup

        // Configure MovieService.save(...).
        final MovieDTO movieDTO = new MovieDTO("kien");
        when(mockMovieService.save(any(MovieDTO.class)))
                .thenReturn(movieDTO);
        String content = "{\n" +
                "    \"nameEng\": \"kien\",\n" +
                "    \"fromDate\": \"2020-10-10\",\n" +
                "    \"toDate\": \"2020-10-10\",\n" +
                "    \"actor\": \"kiendo\",\n" +
                "    \"movieCompany\": \"a\",\n" +
                "    \"director\": \"ab\",\n" +
                "    \"duration\": \"5\",\n" +
                "    \"version\": \"3d\",\n" +
                "    \"cinemaRoom\": {\"id\":\"2\"},\n" +
                "    \"content\": \"ab\",\n" +
                "    \"typeMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"type\": {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"action \",\n" +
                "                \"typeMovies\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"scheduleMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"schedule\": {\n" +
                "                \"id\": 1,\n" +
                "                \"time\": \"11: 00\",\n" +
                "                \"scheduleMovies\": null,\n" +
                "                \"scheduleSeats\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/movies")
                .content(content).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }


    @Test
    public void testAddMovie_OK() throws Exception {
        // Setup
        // Configure MovieService.save(...).
        final MovieDTO movieDTO = new MovieDTO("kien");
        when(mockMovieService.save(any(MovieDTO.class)))
                .thenReturn(movieDTO);
        String content = "{\n" +
                "    \"nameEng\": \"kien\",\n" +
                "    \"nameVN\": \"kien\",\n" +
                "    \"fromDate\": \"2020-10-10\",\n" +
                "    \"toDate\": \"2020-10-10\",\n" +
                "    \"actor\": \"ádasdsadsadsadasdasdasdsadasdsadasdsad\",\n" +
                "    \"movieCompany\": \"a\",\n" +
                "    \"director\": \"ab\",\n" +
                "    \"duration\": \"5\",\n" +
                "    \"version\": \"3d\",\n" +
                "    \"cinemaRoom\": {\"id\":\"2\"},\n" +
                "    \"content\": \"ab\",\n" +
                "    \"typeMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"type\": {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"action \",\n" +
                "                \"typeMovies\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"scheduleMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"schedule\": {\n" +
                "                \"id\": 1,\n" +
                "                \"time\": \"11: 00\",\n" +
                "                \"scheduleMovies\": null,\n" +
                "                \"scheduleSeats\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        //expected, all field is null, actor=kien
        String expected="{\"cinemaRoom\":null,\"id\":null,\"actor\":\"kien\",\"content\":null,\"director\":null,\"duration\":null,\"fromDate\":null,\"toDate\":null,\"movieCompany\":null,\"version\":null,\"nameEng\":null,\"nameVN\":null,\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}";
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/movies")
                .content(content).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }

    @Test
    public void testUpdateMovie_ACCEPTED() throws Exception {
        // Setup

        // Configure MovieService.update(...).
        final MovieDTO movieDTO =
                new MovieDTO("kien");
        when(mockMovieService.update(any(MovieDTO.class), any(Integer.class)))
                .thenReturn(movieDTO);
        // Run the test
        String content = "{\n" +
                "    \"nameEng\": \"kien\",\n" +
                "    \"nameVN\": \"kien\",\n" +
                "    \"fromDate\": \"2020-10-10\",\n" +
                "    \"toDate\": \"2020-10-10\",\n" +
                "    \"actor\": \"ádasdsadsadsadasdasdasdsadasdsadasdsad\",\n" +
                "    \"movieCompany\": \"a\",\n" +
                "    \"director\": \"ab\",\n" +
                "    \"duration\": \"5\",\n" +
                "    \"version\": \"3d\",\n" +
                "    \"cinemaRoom\": {\"id\":\"2\"},\n" +
                "    \"content\": \"ab\",\n" +
                "    \"typeMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"type\": {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"action \",\n" +
                "                \"typeMovies\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"scheduleMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"schedule\": {\n" +
                "                \"id\": 1,\n" +
                "                \"time\": \"11: 00\",\n" +
                "                \"scheduleMovies\": null,\n" +
                "                \"scheduleSeats\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        final MockHttpServletResponse response =
                mockMvc.perform(put("/api/movies/{id}", 0)
                        .content(content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        String expected = "{\"cinemaRoom\":null,\"id\":null,\"actor\":\"kien\",\"content\":null,\"director\":null,\"duration\":null,\"fromDate\":null,\"toDate\":null,\"movieCompany\":null,\"version\":null,\"nameEng\":null,\"nameVN\":null,\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null}";
        // Verify the results
        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }


    @Test
    public void testUpdateMovie_BADREQUEST() throws Exception {
        // Setup

        // Configure MovieService.update(...).

        when(mockMovieService.update(any(MovieDTO.class), any(Integer.class)))
                .thenReturn(null);
        // Run the test
        String content = "{\n" +
                "    \"nameEng\": \"kien\",\n" +
                "    \"fromDate\": \"2020-10-10\",\n" +
                "    \"toDate\": \"2020-10-10\",\n" +
                "    \"actor\": \"ádasdsadsadsadasdasdasdsadasdsadasdsad\",\n" +
                "    \"movieCompany\": \"a\",\n" +
                "    \"director\": \"ab\",\n" +
                "    \"duration\": \"5\",\n" +
                "    \"version\": \"3d\",\n" +
                "    \"cinemaRoom\": {\"id\":\"2\"},\n" +
                "    \"content\": \"ab\",\n" +
                "    \"typeMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"type\": {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"action \",\n" +
                "                \"typeMovies\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"scheduleMovies\": [\n" +
                "        {\n" +
                "            \"movie\": null,\n" +
                "            \"schedule\": {\n" +
                "                \"id\": 1,\n" +
                "                \"time\": \"11: 00\",\n" +
                "                \"scheduleMovies\": null,\n" +
                "                \"scheduleSeats\": null\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        final MockHttpServletResponse response =
                mockMvc.perform(put("/api/movies/{id}", 0)
                        .content(content).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }





    @Test
    public void testDeleteMovie_OK() throws Exception {
        // Setup

        // Configure MovieService.findById(...).
        final MovieDTO movieDTO =
                new MovieDTO(0, "kiendo", "abc", "kiendo", 0.0f,
                        LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany",
                        "version", "nameEng", "nameVN", "avatar", "image");
        when(mockMovieService.findById(0)).thenReturn(movieDTO);

        when(mockMovieService.delete(0)).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response =
                mockMvc.perform(delete("/api/movies/{movieId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Deleted Customer id"));
    }
    @Test
    public void testDeleteMovie_NOTFOUND() throws Exception {
        // Setup

        // Configure MovieService.findById(...)
        when(mockMovieService.findById(0)).thenReturn(null);

        when(mockMovieService.delete(0)).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response =
                mockMvc.perform(delete("/api/movies/{movieId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
