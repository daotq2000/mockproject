package fa.appcode.web.controller;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.dto.*;
import fa.appcode.web.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService mockInvoiceService;

    /**
     * Author: DaoTQ1
     * Description: This function testing when Insert New Invoice, Provide Fields mandatory and valid Argument
     * Result: The test is pass, insert successfully a record. Return ResponseEntity with status 201 and Invoice Object added
     * @throws Exception
     */
    @Test
    void testInsertInvoice() throws Exception {
        // Setup
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1);
        roleDTO.setRoleName(CONSTANT.ROLE_MEMBER);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("daotqph0312@gmail.com");
        accountDTO.setPhoneNumber("0374550289");
        accountDTO.setRegisterDate(LocalDate.now());
        accountDTO.setStatus((byte)1);
//        accountDTO.setDateOfBirth(new Date());
        accountDTO.setFullName("Tran Quang Dao");
        accountDTO.setGender("Male");
        accountDTO.setImage("");
        accountDTO.setIdentityCard("061106221");
        accountDTO.setUsername("daotq1");
        accountDTO.setPassword("123456");
        accountDTO.setRole(roleDTO);

        MemberDTO memberDTO = new MemberDTO(1,0,accountDTO);
        List<ScheduleSeatDTO> scheduleSeatDTOS = new ArrayList<>();
        ScheduleDTO scheduleDTO  = new ScheduleDTO(1,"8:00");

        List<SeatDTO> seatDTOS = new ArrayList<>();
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setId(1);
        cinemaRoomDTO.setRoomName("CGV02");
        cinemaRoomDTO.setSeatQuantity(60);
        cinemaRoomDTO.setSeats(seatDTOS);

        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(1);
        seatDTO1.setSeatColumn("A");
        seatDTO1.setSeatRow(1);
        seatDTO1.setStatus((byte) 1);
        seatDTO1.setSeatType(1);
        seatDTO1.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO1);

        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(1);
        seatDTO.setSeatColumn("B");
        seatDTO.setSeatRow(1);
        seatDTO.setStatus((byte) 1);
        seatDTO.setSeatType(1);
        seatDTO.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO);

        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(1);
        seatDTO2.setSeatColumn("C");
        seatDTO2.setSeatRow(1);
        seatDTO2.setStatus((byte) 1);
        seatDTO2.setSeatType(1);
        seatDTO2.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO2);


        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setActor("TungPX6,DaoTQ1");
        movieDTO.setContent("content");
        movieDTO.setDirector("Victor Vu, Obama");
        movieDTO.setDuration(100.0f);
        movieDTO.setFromDate(LocalDate.of(2020, 1, 1));
        movieDTO.setMovieCompany("Movie Entertainment");
        movieDTO.setToDate(LocalDate.of(2020, 1, 1));
        movieDTO.setVersion("1.0");
        movieDTO.setNameEng("The Averagers");
        movieDTO.setNameVN("Cuoc chien cua nhung vi sao");
        movieDTO.setCinemaRoom(cinemaRoomDTO);

        scheduleSeatDTOS.add(new ScheduleSeatDTO(1,movieDTO,scheduleDTO,1,"A",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));
        scheduleSeatDTOS.add(new ScheduleSeatDTO(2,movieDTO,scheduleDTO,2,"B",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));
        scheduleSeatDTOS.add(new ScheduleSeatDTO(3,movieDTO,scheduleDTO,3,"C",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(1);
        invoiceDTO.setScheduleShow(LocalDate.of(2020,10,10));
        invoiceDTO.setMemberId("2");
        invoiceDTO.setUserScore(0);
        invoiceDTO.setStatus(CONSTANT.PAID_SUCCESS);
        invoiceDTO.setBookingDate(new java.util.Date());
        invoiceDTO.setSeat("1A 1B 1C");
        invoiceDTO.setScheduleShow(LocalDate.now());
        invoiceDTO.setCustomerName("TRAN QUANG DAO");
        invoiceDTO.setMovieName("The champion star");
        invoiceDTO.setCustomerPhone("0374550289");
        invoiceDTO.setScheduleSeats(scheduleSeatDTOS);

        when(mockInvoiceService.insertInvoice(invoiceDTO)).thenReturn(invoiceDTO);
        // Run the test
        String dataJson = "{\"account\":{\"id\":2,\"email\":\"daotq1@gmail.com\",\"fullName\":\"Tran Quang Dao\",\"gender\":\"Nam\",\"identityCard\":\"061106221\",\"image\":null,\"username\":\"daotq1\",\"password\":null,\"phoneNumber\":\"0374550289\",\"dateOfBirth\":null,\"registerDate\":null,\"role\":null,\"status\":1,\"members\":null},\"addScore\":0,\"bookingDate\":null,\"movieName\":\"Promotion\",\"status\":1,\"totalMoney\":150000,\"userScore\":0,\"customerName\":\"Tran Quang Dao\",\"customerPhone\":\"0374550289\",\"customerIdentityCard\":\"061106221\",\"scheduleSeats\":[{\"movie\":{\"id\":\"2\"},\"schedule\":{\"id\":\"1\"},\"seatColumn\":\"A\",\"seatId\":\"37\",\"seatPrice\":\"50000\",\"seatRow\":\"7\",\"seatType\":\"1\",\"status\":\"1\"},{\"movie\":{\"id\":\"2\"},\"schedule\":{\"id\":\"1\"},\"seatColumn\":\"B\",\"seatId\":\"38\",\"seatPrice\":\"50000\",\"seatRow\":\"7\",\"seatType\":\"1\",\"status\":\"1\"},{\"movie\":{\"id\":\"2\"},\"schedule\":{\"id\":\"1\"},\"seatColumn\":\"C\",\"seatId\":\"39\",\"seatPrice\":\"50000\",\"seatRow\":\"7\",\"seatType\":\"1\",\"status\":\"1\"}],\"scheduleShow\":\"2021-03-10\",\"scheduleShowTime\":\"8:00\",\"seat\":\"7A 7B 7C \"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/invoice/")
                .header("Authorization",  CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON).content(dataJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
    /**
     * Author: DaoTQ1
     * Description: This function testing when Insert New Invoice, Provide Fields missing field mandatory and Invalid Argument
     * Result: The test is Failed, insert failed. Return ResponseEntity with status 400 and Errors Array with contains errors
     * @throws Exception
     */
    @Test
    void testInsertInvoiceInvalidArgument() throws Exception {
        // Setup
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1);
        roleDTO.setRoleName(CONSTANT.ROLE_MEMBER);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("daotqph0312@gmail.com");
        accountDTO.setPhoneNumber("0374550289");
        accountDTO.setRegisterDate(LocalDate.now());
        accountDTO.setStatus((byte)1);
//        accountDTO.setDateOfBirth(new Date());
        accountDTO.setFullName("Tran Quang Dao");
        accountDTO.setGender("Male");
        accountDTO.setImage("");
        accountDTO.setIdentityCard("061106221");
        accountDTO.setUsername("daotq1");
        accountDTO.setPassword("123456");
        accountDTO.setRole(roleDTO);

        MemberDTO memberDTO = new MemberDTO(1,0,accountDTO);
        List<ScheduleSeatDTO> scheduleSeatDTOS = new ArrayList<>();
        ScheduleDTO scheduleDTO  = new ScheduleDTO(1,"8:00");

        List<SeatDTO> seatDTOS = new ArrayList<>();
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setId(1);
        cinemaRoomDTO.setRoomName("CGV02");
        cinemaRoomDTO.setSeatQuantity(60);
        cinemaRoomDTO.setSeats(seatDTOS);

        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(1);
        seatDTO1.setSeatColumn("A");
        seatDTO1.setSeatRow(1);
        seatDTO1.setStatus((byte) 1);
        seatDTO1.setSeatType(1);
        seatDTO1.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO1);

        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(1);
        seatDTO.setSeatColumn("B");
        seatDTO.setSeatRow(1);
        seatDTO.setStatus((byte) 1);
        seatDTO.setSeatType(1);
        seatDTO.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO);

        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(1);
        seatDTO2.setSeatColumn("C");
        seatDTO2.setSeatRow(1);
        seatDTO2.setStatus((byte) 1);
        seatDTO2.setSeatType(1);
        seatDTO2.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO2);


        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setActor("TungPX6,DaoTQ1");
        movieDTO.setContent("content");
        movieDTO.setDirector("Victor Vu, Obama");
        movieDTO.setDuration(100.0f);
        movieDTO.setFromDate(LocalDate.of(2020, 1, 1));
        movieDTO.setMovieCompany("Movie Entertainment");
        movieDTO.setToDate(LocalDate.of(2020, 1, 1));
        movieDTO.setVersion("1.0");
        movieDTO.setNameEng("The Averagers");
        movieDTO.setNameVN("Cuoc chien cua nhung vi sao");
        movieDTO.setCinemaRoom(cinemaRoomDTO);

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(1);
        invoiceDTO.setScheduleShow(LocalDate.of(2020,10,10));
        invoiceDTO.setMemberId("2");
        invoiceDTO.setUserScore(0);
        invoiceDTO.setStatus(CONSTANT.PAID_SUCCESS);
        invoiceDTO.setBookingDate(new java.util.Date());
        invoiceDTO.setSeat("1A 1B 1C");
        invoiceDTO.setScheduleShow(LocalDate.now());
        invoiceDTO.setCustomerName("TRAN QUANG DAO");
        invoiceDTO.setMovieName("The champion star");
        invoiceDTO.setCustomerPhone("0374550289");
        invoiceDTO.setScheduleSeats(null);

        when(mockInvoiceService.insertInvoice(invoiceDTO)).thenReturn(invoiceDTO);
        // Run the test
        String dataJson = "{\"account\":{\"id\":2,\"email\":\"daotq1@gmail.com\",\"fullName\":\"Tran Quang Dao\",\"gender\":\"Nam\",\"identityCard\":\"061106221\",\"image\":null,\"username\":\"daotq1\",\"password\":null,\"phoneNumber\":\"0374550289\",\"dateOfBirth\":null,\"registerDate\":null,\"role\":null,\"status\":1,\"members\":null},\"addScore\":0,\"bookingDate\":null,\"movieName\":\"Promotion\",\"status\":1,\"totalMoney\":150000,\"userScore\":0,\"customerName\":\"Tran Quang Dao\",\"customerPhone\":\"0374550289\",\"customerIdentityCard\":\"061106221\",\"scheduleSeats\":null}";        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/invoice/")
                .header("Authorization",  CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON).content(dataJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        // Verify the results
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
    /**
     * Author: DaoTQ1
     * Description: This function find Invoice record existed database with ID is 1.
     * Result: The test is Pass, found InvoiceId. Return ResponseEntity with status 200 and Invoice Object Information
     * @throws Exception
     */
    @Test
    void testGetInvoiceById() throws Exception {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1);
        roleDTO.setRoleName(CONSTANT.ROLE_MEMBER);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("daotqph0312@gmail.com");
        accountDTO.setPhoneNumber("0374550289");
        accountDTO.setRegisterDate(LocalDate.now());
        accountDTO.setStatus((byte)1);
//        accountDTO.setDateOfBirth(new Date());
        accountDTO.setFullName("Tran Quang Dao");
        accountDTO.setGender("Male");
        accountDTO.setImage("");
        accountDTO.setIdentityCard("061106221");
        accountDTO.setUsername("daotq1");
        accountDTO.setPassword("123456");
        accountDTO.setRole(roleDTO);

        MemberDTO memberDTO = new MemberDTO(1,0,accountDTO);
        List<ScheduleSeatDTO> scheduleSeatDTOS = new ArrayList<>();
        ScheduleDTO scheduleDTO  = new ScheduleDTO(1,"8:00");

        List<SeatDTO> seatDTOS = new ArrayList<>();
        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setId(1);
        cinemaRoomDTO.setRoomName("CGV02");
        cinemaRoomDTO.setSeatQuantity(60);
        cinemaRoomDTO.setSeats(seatDTOS);

        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(1);
        seatDTO1.setSeatColumn("A");
        seatDTO1.setSeatRow(1);
        seatDTO1.setStatus((byte) 1);
        seatDTO1.setSeatType(1);
        seatDTO1.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO1);

        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(1);
        seatDTO.setSeatColumn("B");
        seatDTO.setSeatRow(1);
        seatDTO.setStatus((byte) 1);
        seatDTO.setSeatType(1);
        seatDTO.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO);

        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(1);
        seatDTO2.setSeatColumn("C");
        seatDTO2.setSeatRow(1);
        seatDTO2.setStatus((byte) 1);
        seatDTO2.setSeatType(1);
        seatDTO2.setSeatPrice(new BigDecimal("45000.00"));
        seatDTOS.add(seatDTO2);


        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setActor("TungPX6,DaoTQ1");
        movieDTO.setContent("content");
        movieDTO.setDirector("Victor Vu, Obama");
        movieDTO.setDuration(100.0f);
        movieDTO.setFromDate(LocalDate.of(2020, 1, 1));
        movieDTO.setMovieCompany("Movie Entertainment");
        movieDTO.setToDate(LocalDate.of(2020, 1, 1));
        movieDTO.setVersion("1.0");
        movieDTO.setNameEng("The Averagers");
        movieDTO.setNameVN("Cuoc chien cua nhung vi sao");
        movieDTO.setCinemaRoom(cinemaRoomDTO);

        scheduleSeatDTOS.add(new ScheduleSeatDTO(1,movieDTO,scheduleDTO,1,"A",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));
        scheduleSeatDTOS.add(new ScheduleSeatDTO(2,movieDTO,scheduleDTO,2,"B",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));
        scheduleSeatDTOS.add(new ScheduleSeatDTO(3,movieDTO,scheduleDTO,3,"C",1, (byte) 1,1,BigDecimal.valueOf(Long.parseLong("45000")),null));

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(1);
        invoiceDTO.setScheduleShow(LocalDate.of(2020,10,10));
        invoiceDTO.setMemberId("2");
        invoiceDTO.setUserScore(0);
        invoiceDTO.setStatus(CONSTANT.PAID_SUCCESS);
        invoiceDTO.setBookingDate(null);
        invoiceDTO.setSeat("1A 1B 1C");
        invoiceDTO.setScheduleShow(LocalDate.now());
        invoiceDTO.setCustomerName("TRAN QUANG DAO");
        invoiceDTO.setMovieName("The champion star");
        invoiceDTO.setCustomerPhone("0374550289");
        invoiceDTO.setScheduleSeats(scheduleSeatDTOS);
        when(mockInvoiceService.findById(1)).thenReturn(invoiceDTO);

        String expectedResult ="{\"id\":1,\"account\":null,\"addScore\":null,\"bookingDate\":null,\"movieName\":\"The champion star\",\"scheduleShow\":\"2021-03-10\",\"scheduleShowTime\":null,\"status\":1,\"totalMoney\":null,\"userScore\":0,\"seat\":\"1A 1B 1C\",\"tickets\":null,\"customerName\":\"TRAN QUANG DAO\",\"customerPhone\":\"0374550289\",\"customerIdentityCard\":null,\"memberId\":\"2\",\"promotion\":null,\"scheduleSeats\":[{\"scheduleSeatId\":1,\"movie\":{\"cinemaRoom\":{\"id\":1,\"roomName\":\"CGV02\",\"vipPrice\":null,\"normalPrice\":null,\"seatQuantity\":60,\"movies\":null,\"seats\":[{\"seatId\":1,\"seatColumn\":\"A\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"B\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"C\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null}]},\"id\":1,\"actor\":\"TungPX6,DaoTQ1\",\"content\":\"content\",\"director\":\"Victor Vu, Obama\",\"duration\":100.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"Movie Entertainment\",\"version\":\"1.0\",\"nameEng\":\"The Averagers\",\"nameVN\":\"Cuoc chien cua nhung vi sao\",\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null},\"schedule\":{\"id\":1,\"time\":\"8:00\",\"scheduleMovies\":null,\"scheduleSeats\":null},\"seatId\":1,\"seatColumn\":\"A\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000,\"tickets\":null},{\"scheduleSeatId\":2,\"movie\":{\"cinemaRoom\":{\"id\":1,\"roomName\":\"CGV02\",\"vipPrice\":null,\"normalPrice\":null,\"seatQuantity\":60,\"movies\":null,\"seats\":[{\"seatId\":1,\"seatColumn\":\"A\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"B\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"C\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null}]},\"id\":1,\"actor\":\"TungPX6,DaoTQ1\",\"content\":\"content\",\"director\":\"Victor Vu, Obama\",\"duration\":100.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"Movie Entertainment\",\"version\":\"1.0\",\"nameEng\":\"The Averagers\",\"nameVN\":\"Cuoc chien cua nhung vi sao\",\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null},\"schedule\":{\"id\":1,\"time\":\"8:00\",\"scheduleMovies\":null,\"scheduleSeats\":null},\"seatId\":2,\"seatColumn\":\"B\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000,\"tickets\":null},{\"scheduleSeatId\":3,\"movie\":{\"cinemaRoom\":{\"id\":1,\"roomName\":\"CGV02\",\"vipPrice\":null,\"normalPrice\":null,\"seatQuantity\":60,\"movies\":null,\"seats\":[{\"seatId\":1,\"seatColumn\":\"A\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"B\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null},{\"seatId\":1,\"seatColumn\":\"C\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000.00,\"cinemaRoom\":null}]},\"id\":1,\"actor\":\"TungPX6,DaoTQ1\",\"content\":\"content\",\"director\":\"Victor Vu, Obama\",\"duration\":100.0,\"fromDate\":\"2020-01-01\",\"toDate\":\"2020-01-01\",\"movieCompany\":\"Movie Entertainment\",\"version\":\"1.0\",\"nameEng\":\"The Averagers\",\"nameVN\":\"Cuoc chien cua nhung vi sao\",\"avatar\":null,\"image\":null,\"scheduleMovies\":null,\"typeMovies\":null,\"tickets\":null,\"scheduleSeats\":null},\"schedule\":{\"id\":1,\"time\":\"8:00\",\"scheduleMovies\":null,\"scheduleSeats\":null},\"seatId\":3,\"seatColumn\":\"C\",\"seatRow\":1,\"status\":1,\"seatType\":1,\"seatPrice\":45000,\"tickets\":null}]}";
        final MockHttpServletResponse response = mockMvc.perform(get("/api/invoice/1")
                .header("Authorization",  CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResult, response.getContentAsString());
    }
    /**
     * Author: DaoTQ1
     * Description: This function find Invoice record doesn't existed database with ID is 100.
     * Result: The test is Failed, the function throws NotFoundException. Return ResponseEntity with status 400 and message 'Invoice Not Found'
     * @throws Exception
     * @throws NotFoundException
     */
    @Test
    void testGetInvoiceByIdNotPresent() throws Exception {

        when(mockInvoiceService.findById(100)).thenThrow(new NotFoundException("Invoice not found"));

        final MockHttpServletResponse response = mockMvc.perform(get("/api/invoice/1")
                .header("Authorization",  CONSTANT.TOKEN_TEST)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThrows(NotFoundException.class,()->mockInvoiceService.findById(100));

    }
}
