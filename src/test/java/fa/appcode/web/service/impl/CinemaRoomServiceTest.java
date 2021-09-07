package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.CinemaRoomConverter;
import fa.appcode.web.dto.CinemaRoomDTO;
import fa.appcode.web.dto.SeatDTO;
import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.entities.Seat;
import fa.appcode.web.repository.CinemaRoomRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@RunWith(SpringRunner.class)
@SpringBootTest
class CinemaRoomServiceTest {
    @Mock
    CinemaRoomRepository cinemaRoomRepository;
    @InjectMocks
    CinemaRoomServiceImpl cinemaRoomService;
    @Mock
    private CinemaRoomConverter cinemaRoomConverter;
    private static List<CinemaRoom> cinemaRooms;
    @BeforeEach
    public void setUp() {
        System.out.println("set up");
        MockitoAnnotations.initMocks(this);
    }
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        cinemaRooms = new ArrayList<>();
        cinemaRooms.add(
                new CinemaRoom(1, "cinemaRoom1", 54)
        );
        cinemaRooms.add(
                new CinemaRoom(2, "cinemaRoom2", 72)
        );
        cinemaRooms.add(
                new CinemaRoom(3, "cinemaRoom3", 60)
        );
        cinemaRooms.add(
                new CinemaRoom(4, "cinemaRoom4", 60)
        );
        cinemaRooms.add(
                new CinemaRoom(5, "cinemaRoom5", 60)
        );
    }
    @Test
    public void testSaveCinemaRoom()  {
        CinemaRoom cinemaRoom = new CinemaRoom();
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A",1, (byte) 1,1,BigDecimal.valueOf(50000)));
        cinemaRoom.setSeats(seats);
        cinemaRoom.setRoomName("room1");
        cinemaRoom.setSeatQuantity(1);
        Mockito.when(cinemaRoomRepository.save(cinemaRoom)).thenReturn(cinemaRoom);

        CinemaRoomDTO cinemaRoomDTOSave = new CinemaRoomDTO();
        List<SeatDTO> seatDTOS = new ArrayList<>();
        seatDTOS.add(new SeatDTO("A",1, (byte) 1,1,BigDecimal.valueOf(50000)));
        cinemaRoomDTOSave.setSeats(seatDTOS);
        cinemaRoomDTOSave.setSeatQuantity(1);
        cinemaRoomDTOSave.setRoomName("room1");
        Mockito.when(cinemaRoomConverter.convertToEntity(cinemaRoomDTOSave)).thenReturn(cinemaRoom);
        CinemaRoomDTO actual = cinemaRoomService.save(cinemaRoomDTOSave);

        assertEquals(cinemaRoomConverter.convertToAllDependencies(cinemaRoom), actual);
    }
    // find by room name
    @Test
    public void testFindByRoomNameWithExistingRoomName() {
        String roomName = "room";
        Page<CinemaRoom> expected = Mockito.mock(Page.class);
        Mockito.when(cinemaRoomRepository.findByRoomNameLike(roomName, PageRequest.of(0, 5))).thenReturn(expected);
        Page<CinemaRoom> actual = cinemaRoomService.findAllByNameContaining(5, 0, roomName);
        assertEquals(expected, actual);
    }
    @Test
    public void testFindByRoomNameWithNotExistingRoomName() {
        String roomName = "123";
        Page<CinemaRoom> expected = Mockito.mock(Page.class);
        Mockito.when(cinemaRoomRepository.findByRoomNameLike(roomName, PageRequest.of(0, 5))).thenReturn(expected);
        Page<CinemaRoom> actual = cinemaRoomService.findAllByNameContaining(5, 0, roomName);
        assertEquals(expected, actual);
    }
    @Test
    public void testFindByRoomNameWithEmptyRoomName() throws ParseException {
        String roomName = "";
        Page<CinemaRoom> expected = Mockito.mock(Page.class);
        Mockito.when(cinemaRoomRepository.findByRoomNameLike(roomName, PageRequest.of(0, 5))).thenReturn(expected);
        Page<CinemaRoom> actual = cinemaRoomService.findAllByNameContaining(5, 0, roomName);
        assertEquals(expected, actual);
    }
    // find by id method
    @Test
    public void testFindById() {
        CinemaRoom expected = cinemaRooms.get(0);
        Mockito.when(cinemaRoomRepository.findById(1)).thenReturn(Optional.of(expected));
        CinemaRoomDTO actual = cinemaRoomService.findById(1);
        assertEquals(cinemaRoomConverter.convertToDTO(expected), actual);
    }
    @Test
    public void testFindByIdWithNotExistId() {
        Mockito.when(cinemaRoomRepository.findById(-1)).thenReturn(Optional.empty());
        CinemaRoomDTO actual = cinemaRoomService.findById(-1);
        assertEquals(null, actual);
    }
    // delete method
    @Test
    public void testDeleteWithExistId() {
        int id = 1;
        Mockito.when(cinemaRoomRepository.findById(id)).thenReturn(Optional.of(Mockito.mock(CinemaRoom.class)));
        boolean actual = cinemaRoomService.delete(id);
        assertEquals(true, actual);
    }
    @Test
    public void testDeleteWithNotExistId() throws NotFoundException{
        int id = 1;
        Mockito.when(cinemaRoomRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cinemaRoomService.delete(1));
    }
}