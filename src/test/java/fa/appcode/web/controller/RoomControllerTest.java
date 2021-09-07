package fa.appcode.web.controller;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.controller.RoomController;
import fa.appcode.web.converter.CinemaRoomConverter;
import fa.appcode.web.dto.CinemaRoomDTO;
import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.repository.CinemaRoomRepository;
import fa.appcode.web.service.CinemaRoomService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoomControllerTest {
    @InjectMocks
    private RoomController roomController;
    @Mock
    private CinemaRoomService cinemaRoomService;
    @Mock
    private CinemaRoomConverter cinemaRoomConverter;
    @Autowired
    private MockMvc mockMvc;
    private  static List<CinemaRoom> cinemaRooms;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        cinemaRooms = new ArrayList<>();
        cinemaRooms.add(
                new CinemaRoom(1,"cinemaRoom1",54)
        );
        cinemaRooms.add(
                new CinemaRoom(2,"cinemaRoom2",72)
        );
        cinemaRooms.add(
                new CinemaRoom(3,"cinemaRoom3",60)
        );
    }
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }
    @BeforeEach
    void setUp() throws Exception {
    }
    @AfterEach
    void tearDown() throws Exception {
    }
    @Test
    public void testDeleteById()  {
        when(cinemaRoomService.delete(10)).thenReturn(true);
        String actual = roomController.deleteCinemaRoom(10).getBody();
        assertEquals("Delete Success",actual);
    }
    @Test
    public void testDeleteByIdWithMinusId() throws Exception {
        when(cinemaRoomService.delete(-10)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> roomController.deleteCinemaRoom(-10));
    }
    @Test
    public void testDeleteByIdWithNotExistId() throws Exception {
        when(cinemaRoomService.delete(1000)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> roomController.deleteCinemaRoom(1000));
    }

    @Test
    public void testFindById()  {
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO(1,"room1",BigDecimal.valueOf(45000),BigDecimal.valueOf(50000),54);
        when(cinemaRoomService.findById(10)).thenReturn(cinemaRoomDTO);
        CinemaRoomDTO actual = roomController.getByIdRoom(10).getBody();
        assertEquals(cinemaRoomDTO,actual);
    }

    @Test
    public void testFindByIdWithWithNotExistId() {
        when(cinemaRoomService.findById(10000)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> roomController.getByIdRoom(10000));
    }
    @Test
    public void testFindByIdWithNotExistId2() {
        when(cinemaRoomService.findById(-1)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> roomController.getByIdRoom(-1));
    }

    @Test
    public void testFindAll() {
        List<CinemaRoom> cinemaRoomList = new ArrayList<>();
        cinemaRoomList.add(
                new CinemaRoom(1,"cinemaRoom1",54)
        );
        cinemaRoomList.add(
                new CinemaRoom(2,"cinemaRoom2",72)
        );
        cinemaRoomList.add(
                new CinemaRoom(3,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(4,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(5,"cinemaRoom3",60)
        );
        Page<CinemaRoom> cinemaRoomPage1 = new PageImpl<>(cinemaRoomList);
        when(cinemaRoomService.findAllByNameContaining(5,0,"")).thenReturn(cinemaRoomPage1);
        Page<CinemaRoomDTO> pageDTO = cinemaRoomPage1.map(cinemaRoom -> cinemaRoomConverter.convertToDTO(cinemaRoom));
        Page<CinemaRoomDTO> actual = roomController.getListCinemaRoomPaging(5,0,"");
        assertEquals(actual, pageDTO);
    }
    @Test
    public void testFindAllWithCinemaRoomName() {
        List<CinemaRoom> cinemaRoomList = new ArrayList<>();
        cinemaRoomList.add(
                new CinemaRoom(1,"cinemaRoom1",54)
        );
        cinemaRoomList.add(
                new CinemaRoom(2,"cinemaRoom2",72)
        );
        cinemaRoomList.add(
                new CinemaRoom(3,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(4,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(5,"cinemaRoom3",60)
        );
        Page<CinemaRoom> cinemaRoomPage1 = new PageImpl<>(cinemaRoomList);
        when(cinemaRoomService.findAllByNameContaining(5,0,"cinema")).thenReturn(cinemaRoomPage1);
        Page<CinemaRoomDTO> pageDTO = cinemaRoomPage1.map(cinemaRoom -> cinemaRoomConverter.convertToDTO(cinemaRoom));
        Page<CinemaRoomDTO> actual = roomController.getListCinemaRoomPaging(5,0,"cinema");
        assertEquals(actual, pageDTO);
    }
    @Test
    public void testFindAllWithNotExistCinemaRoomName() {
        List<CinemaRoom> cinemaRoomList = new ArrayList<>();
        cinemaRoomList.add(
                new CinemaRoom(1,"cinemaRoom1",54)
        );
        cinemaRoomList.add(
                new CinemaRoom(2,"cinemaRoom2",72)
        );
        cinemaRoomList.add(
                new CinemaRoom(3,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(4,"cinemaRoom3",60)
        );
        cinemaRoomList.add(
                new CinemaRoom(5,"cinemaRoom3",60)
        );
        Page<CinemaRoom> cinemaRoomPage1 = new PageImpl<>(cinemaRoomList);
        when(cinemaRoomService.findAllByNameContaining(5,0,"abc")).thenReturn(cinemaRoomPage1);
        Page<CinemaRoomDTO> pageDTO = cinemaRoomPage1.map(cinemaRoom -> cinemaRoomConverter.convertToDTO(cinemaRoom));
        Page<CinemaRoomDTO> actual = roomController.getListCinemaRoomPaging(5,0,"abc");
        assertEquals(actual, pageDTO);
    }
    @Test
    public void testSaveCinemaRoom() throws Exception {
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO(1,"room1", BigDecimal.valueOf(45000),BigDecimal.valueOf(50000),54);
        when(cinemaRoomService.save(cinemaRoomDTO)).thenReturn(cinemaRoomDTO);
        String cinemaRoomJSON = "{\"id\":1,\"roomName\":\"room1\",\"normalPrice\":45000, \"vipPrice\":50000,"
                + " \"seatQuantity\":4}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/cinemaRoom/addRoom")
                .accept(MediaType.APPLICATION_JSON).content(cinemaRoomJSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    @Test
    public void testSaveCinemaRoomInvalid() throws Exception {
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO(1,"room1", BigDecimal.valueOf(45000),BigDecimal.valueOf(50000),54);
        when(cinemaRoomService.save(cinemaRoomDTO)).thenReturn(cinemaRoomDTO);
        String cinemaRoomJSON = "{\"id\":1,\"roomName\":,\"normalPrice\":45000, \"vipPrice\":50000,"
                + " \"seatQuantity\":}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/cinemaRoom/addRoom")
                .accept(MediaType.APPLICATION_JSON).content(cinemaRoomJSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}