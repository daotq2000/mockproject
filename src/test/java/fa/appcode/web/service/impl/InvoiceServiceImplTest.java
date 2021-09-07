package fa.appcode.web.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.InvoiceConverter;
import fa.appcode.web.converter.ScheduleSeatConverter;
import fa.appcode.web.dto.*;
import fa.appcode.web.entities.*;
import fa.appcode.web.repository.AccountRepository;
import fa.appcode.web.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository mockInvoiceRepository;
    @Mock
    private InvoiceConverter mockInvoiceConverter;
    @Mock
    private ScheduleSeatConverter mockScheduleSeatConverter;
    @Mock
    private AccountRepository mockAccountRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceImplUnderTest;
    /**
     * Author: DaoTQ1
     * Description: This function testing when Insert New Invoice, Provide Fields mandatory and valid Argument
     * Result: The test is pass, insert successfully a record. return InvoiceDTO object
     */
    @Test
    void testInsertInvoice() {
        // Setup
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));

        // Configure AccountRepository.getOne(...).
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        when(mockAccountRepository.getOne(0)).thenReturn(account);

        // Configure InvoiceConverter.convertToEntity(...).
        final Invoice invoice = new Invoice();
        invoice.setId(0);
        final Account account1 = new Account();
        account1.setId(0);
        account1.setAddress("address");
        account1.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account1.setEmail("email");
        account1.setFullName("fullName");
        account1.setGender("gender");
        account1.setIdentityCard("identityCard");
        account1.setImage("image");
        account1.setUsername("username");
        account1.setPassword("password");
        invoice.setAccount(account1);
        invoice.setAddScore(0);
        invoice.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice.setMovieName("movieName");
        invoice.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice.setScheduleShowTime("scheduleShowTime");
        invoice.setStatus((byte) 0b0);
        invoice.setTotalMoney(new BigDecimal("0.00"));
        invoice.setUserScore(0);
        when(mockInvoiceConverter.convertToEntity(any(InvoiceDTO.class))).thenReturn(invoice);

        // Configure ScheduleSeatConverter.convertToEntity(...).
        final Movie movie = new Movie();
        movie.setId(0);
        movie.setActor("actor");
        movie.setContent("content");
        movie.setDirector("director");
        movie.setDuration(0.0f);
        movie.setFromDate(LocalDate.of(2020, 1, 1));
        movie.setMovieCompany("movieCompany");
        movie.setToDate(LocalDate.of(2020, 1, 1));
        movie.setVersion("version");
        movie.setNameEng("nameEng");
        final Schedule schedule = new Schedule();
        schedule.setId(0);
        schedule.setTime("time");
        final Movie movie1 = new Movie();
        movie1.setId(0);
        movie1.setActor("actor");
        movie1.setContent("content");
        movie1.setDirector("director");
        movie1.setDuration(0.0f);
        movie1.setFromDate(LocalDate.of(2020, 1, 1));
        movie1.setMovieCompany("movieCompany");
        movie1.setToDate(LocalDate.of(2020, 1, 1));
        movie1.setVersion("version");
        movie1.setNameEng("nameEng");
        schedule.setScheduleMovies(Arrays.asList(new ScheduleMovie(new ScheduleMovieId(movie1, new Schedule()))));
        final Movie movie2 = new Movie();
        movie2.setId(0);
        movie2.setActor("actor");
        movie2.setContent("content");
        movie2.setDirector("director");
        movie2.setDuration(0.0f);
        movie2.setFromDate(LocalDate.of(2020, 1, 1));
        movie2.setMovieCompany("movieCompany");
        movie2.setToDate(LocalDate.of(2020, 1, 1));
        movie2.setVersion("version");
        movie2.setNameEng("nameEng");
        final Ticket ticket = new Ticket();
        ticket.setTicketId(0);
        final Movie movie3 = new Movie();
        movie3.setId(0);
        movie3.setActor("actor");
        movie3.setContent("content");
        movie3.setDirector("director");
        movie3.setDuration(0.0f);
        movie3.setFromDate(LocalDate.of(2020, 1, 1));
        movie3.setMovieCompany("movieCompany");
        movie3.setToDate(LocalDate.of(2020, 1, 1));
        movie3.setVersion("version");
        movie3.setNameEng("nameEng");
        ticket.setScheduleSeat(new ScheduleSeat(0, movie3, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket())));
        final Invoice invoice1 = new Invoice();
        invoice1.setId(0);
        final Account account2 = new Account();
        account2.setId(0);
        account2.setAddress("address");
        account2.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account2.setEmail("email");
        account2.setFullName("fullName");
        account2.setGender("gender");
        account2.setIdentityCard("identityCard");
        account2.setImage("image");
        account2.setUsername("username");
        account2.setPassword("password");
        invoice1.setAccount(account2);
        invoice1.setAddScore(0);
        invoice1.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice1.setMovieName("movieName");
        invoice1.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice1.setScheduleShowTime("scheduleShowTime");
        invoice1.setStatus((byte) 0b0);
        invoice1.setTotalMoney(new BigDecimal("0.00"));
        invoice1.setUserScore(0);
        ticket.setInvoice(invoice1);
        ticket.setPrice(new BigDecimal("0.00"));
        schedule.setSchedules(Arrays.asList(new ScheduleSeat(0, movie2, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(ticket))));
        final Ticket ticket1 = new Ticket();
        ticket1.setTicketId(0);
        final Movie movie4 = new Movie();
        movie4.setId(0);
        movie4.setActor("actor");
        movie4.setContent("content");
        movie4.setDirector("director");
        movie4.setDuration(0.0f);
        movie4.setFromDate(LocalDate.of(2020, 1, 1));
        movie4.setMovieCompany("movieCompany");
        movie4.setToDate(LocalDate.of(2020, 1, 1));
        movie4.setVersion("version");
        movie4.setNameEng("nameEng");
        final Schedule schedule1 = new Schedule();
        schedule1.setId(0);
        schedule1.setTime("time");
        final Movie movie5 = new Movie();
        movie5.setId(0);
        movie5.setActor("actor");
        movie5.setContent("content");
        movie5.setDirector("director");
        movie5.setDuration(0.0f);
        movie5.setFromDate(LocalDate.of(2020, 1, 1));
        movie5.setMovieCompany("movieCompany");
        movie5.setToDate(LocalDate.of(2020, 1, 1));
        movie5.setVersion("version");
        movie5.setNameEng("nameEng");
        schedule1.setScheduleMovies(Arrays.asList(new ScheduleMovie(new ScheduleMovieId(movie5, new Schedule()))));
        final Movie movie6 = new Movie();
        movie6.setId(0);
        movie6.setActor("actor");
        movie6.setContent("content");
        movie6.setDirector("director");
        movie6.setDuration(0.0f);
        movie6.setFromDate(LocalDate.of(2020, 1, 1));
        movie6.setMovieCompany("movieCompany");
        movie6.setToDate(LocalDate.of(2020, 1, 1));
        movie6.setVersion("version");
        movie6.setNameEng("nameEng");
        schedule1.setSchedules(Arrays.asList(new ScheduleSeat(0, movie6, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket()))));
        ticket1.setScheduleSeat(new ScheduleSeat(0, movie4, schedule1, 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket())));
        final Invoice invoice2 = new Invoice();
        invoice2.setId(0);
        final Account account3 = new Account();
        account3.setId(0);
        account3.setAddress("address");
        account3.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account3.setEmail("email");
        account3.setFullName("fullName");
        account3.setGender("gender");
        account3.setIdentityCard("identityCard");
        account3.setImage("image");
        account3.setUsername("username");
        account3.setPassword("password");
        invoice2.setAccount(account3);
        invoice2.setAddScore(0);
        invoice2.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice2.setMovieName("movieName");
        invoice2.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice2.setScheduleShowTime("scheduleShowTime");
        invoice2.setStatus((byte) 0b0);
        invoice2.setTotalMoney(new BigDecimal("0.00"));
        invoice2.setUserScore(0);
        ticket1.setInvoice(invoice2);
        ticket1.setPrice(new BigDecimal("0.00"));
        final ScheduleSeat scheduleSeat = new ScheduleSeat(0, movie, schedule, 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(ticket1));
        when(mockScheduleSeatConverter.convertToEntity(any(ScheduleSeatDTO.class))).thenReturn(scheduleSeat);

        // Configure InvoiceRepository.save(...).
        final Invoice invoice3 = new Invoice();
        invoice3.setId(0);
        final Account account4 = new Account();
        account4.setId(0);
        account4.setAddress("address");
        account4.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account4.setEmail("email");
        account4.setFullName("fullName");
        account4.setGender("gender");
        account4.setIdentityCard("identityCard");
        account4.setImage("image");
        account4.setUsername("username");
        account4.setPassword("password");
        invoice3.setAccount(account4);
        invoice3.setAddScore(0);
        invoice3.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice3.setMovieName("movieName");
        invoice3.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice3.setScheduleShowTime("scheduleShowTime");
        invoice3.setStatus((byte) 0b0);
        invoice3.setTotalMoney(new BigDecimal("0.00"));
        invoice3.setUserScore(0);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice3);

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO5 = new SeatDTO();
        seatDTO5.setSeatId(0);
        seatDTO5.setSeatColumn("seatColumn");
        seatDTO5.setSeatRow(0);
        seatDTO5.setStatus((byte) 0b0);
        seatDTO5.setSeatType(0);
        seatDTO5.setSeatPrice(new BigDecimal("0.00"));
        seatDTO5.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO6 = new SeatDTO();
        seatDTO6.setSeatId(0);
        seatDTO6.setSeatColumn("seatColumn");
        seatDTO6.setSeatRow(0);
        seatDTO6.setStatus((byte) 0b0);
        seatDTO6.setSeatType(0);
        seatDTO6.setSeatPrice(new BigDecimal("0.00"));
        seatDTO6.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO7 = new SeatDTO();
        seatDTO7.setSeatId(0);
        seatDTO7.setSeatColumn("seatColumn");
        seatDTO7.setSeatRow(0);
        seatDTO7.setStatus((byte) 0b0);
        seatDTO7.setSeatType(0);
        seatDTO7.setSeatPrice(new BigDecimal("0.00"));
        seatDTO7.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO8 = new SeatDTO();
        seatDTO8.setSeatId(0);
        seatDTO8.setSeatColumn("seatColumn");
        seatDTO8.setSeatRow(0);
        seatDTO8.setStatus((byte) 0b0);
        seatDTO8.setSeatType(0);
        seatDTO8.setSeatPrice(new BigDecimal("0.00"));
        seatDTO8.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO9 = new SeatDTO();
        seatDTO9.setSeatId(0);
        seatDTO9.setSeatColumn("seatColumn");
        seatDTO9.setSeatRow(0);
        seatDTO9.setStatus((byte) 0b0);
        seatDTO9.setSeatType(0);
        seatDTO9.setSeatPrice(new BigDecimal("0.00"));
        seatDTO9.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO1 = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO5)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO6)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO7)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO8)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO9)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO1);

        // Run the test
        final InvoiceDTO result = invoiceServiceImplUnderTest.insertInvoice(invoiceDTO);

        // Verify the results
        assertEquals(result, invoiceDTO);
    }
    /**
     * Author: DaoTQ1
     * Description: This function testing when Insert New Invoice, Provide Fields missing field ScheduleSeats and Invalid Argument
     * Result: The test is Failed, insert failed. Throws new IllegalArgumentException with message 'ScheduleSeats cannot be null'
     * @throws Exception
     * @throws IllegalArgumentException
     */
    @Test
    void testInsertInvoiceMissingScheduleSeats() {
        // Setup
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));

        // Configure AccountRepository.getOne(...).
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        when(mockAccountRepository.getOne(0)).thenReturn(account);

        // Configure InvoiceConverter.convertToEntity(...).
        final Invoice invoice = new Invoice();
        invoice.setId(0);
        final Account account1 = new Account();
        account1.setId(0);
        account1.setAddress("address");
        account1.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account1.setEmail("email");
        account1.setFullName("fullName");
        account1.setGender("gender");
        account1.setIdentityCard("identityCard");
        account1.setImage("image");
        account1.setUsername("username");
        account1.setPassword("password");
        invoice.setAccount(account1);
        invoice.setAddScore(0);
        invoice.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice.setMovieName("movieName");
        invoice.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice.setScheduleShowTime("scheduleShowTime");
        invoice.setStatus((byte) 0b0);
        invoice.setTotalMoney(new BigDecimal("0.00"));
        invoice.setUserScore(0);
        when(mockInvoiceConverter.convertToEntity(any(InvoiceDTO.class))).thenReturn(invoice);

        // Configure ScheduleSeatConverter.convertToEntity(...).
        final Movie movie = new Movie();
        movie.setId(0);
        movie.setActor("actor");
        movie.setContent("content");
        movie.setDirector("director");
        movie.setDuration(0.0f);
        movie.setFromDate(LocalDate.of(2020, 1, 1));
        movie.setMovieCompany("movieCompany");
        movie.setToDate(LocalDate.of(2020, 1, 1));
        movie.setVersion("version");
        movie.setNameEng("nameEng");
        final Schedule schedule = new Schedule();
        schedule.setId(0);
        schedule.setTime("time");
        final Movie movie1 = new Movie();
        movie1.setId(0);
        movie1.setActor("actor");
        movie1.setContent("content");
        movie1.setDirector("director");
        movie1.setDuration(0.0f);
        movie1.setFromDate(LocalDate.of(2020, 1, 1));
        movie1.setMovieCompany("movieCompany");
        movie1.setToDate(LocalDate.of(2020, 1, 1));
        movie1.setVersion("version");
        movie1.setNameEng("nameEng");
        schedule.setScheduleMovies(Arrays.asList(new ScheduleMovie(new ScheduleMovieId(movie1, new Schedule()))));
        final Movie movie2 = new Movie();
        movie2.setId(0);
        movie2.setActor("actor");
        movie2.setContent("content");
        movie2.setDirector("director");
        movie2.setDuration(0.0f);
        movie2.setFromDate(LocalDate.of(2020, 1, 1));
        movie2.setMovieCompany("movieCompany");
        movie2.setToDate(LocalDate.of(2020, 1, 1));
        movie2.setVersion("version");
        movie2.setNameEng("nameEng");
        final Ticket ticket = new Ticket();
        ticket.setTicketId(0);
        final Movie movie3 = new Movie();
        movie3.setId(0);
        movie3.setActor("actor");
        movie3.setContent("content");
        movie3.setDirector("director");
        movie3.setDuration(0.0f);
        movie3.setFromDate(LocalDate.of(2020, 1, 1));
        movie3.setMovieCompany("movieCompany");
        movie3.setToDate(LocalDate.of(2020, 1, 1));
        movie3.setVersion("version");
        movie3.setNameEng("nameEng");
        ticket.setScheduleSeat(new ScheduleSeat(0, movie3, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket())));
        final Invoice invoice1 = new Invoice();
        invoice1.setId(0);
        final Account account2 = new Account();
        account2.setId(0);
        account2.setAddress("address");
        account2.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account2.setEmail("email");
        account2.setFullName("fullName");
        account2.setGender("gender");
        account2.setIdentityCard("identityCard");
        account2.setImage("image");
        account2.setUsername("username");
        account2.setPassword("password");
        invoice1.setAccount(account2);
        invoice1.setAddScore(0);
        invoice1.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice1.setMovieName("movieName");
        invoice1.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice1.setScheduleShowTime("scheduleShowTime");
        invoice1.setStatus((byte) 0b0);
        invoice1.setTotalMoney(new BigDecimal("0.00"));
        invoice1.setUserScore(0);
        ticket.setInvoice(invoice1);
        ticket.setPrice(new BigDecimal("0.00"));
        schedule.setSchedules(Arrays.asList(new ScheduleSeat(0, movie2, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(ticket))));
        final Ticket ticket1 = new Ticket();
        ticket1.setTicketId(0);
        final Movie movie4 = new Movie();
        movie4.setId(0);
        movie4.setActor("actor");
        movie4.setContent("content");
        movie4.setDirector("director");
        movie4.setDuration(0.0f);
        movie4.setFromDate(LocalDate.of(2020, 1, 1));
        movie4.setMovieCompany("movieCompany");
        movie4.setToDate(LocalDate.of(2020, 1, 1));
        movie4.setVersion("version");
        movie4.setNameEng("nameEng");
        final Schedule schedule1 = new Schedule();
        schedule1.setId(0);
        schedule1.setTime("time");
        final Movie movie5 = new Movie();
        movie5.setId(0);
        movie5.setActor("actor");
        movie5.setContent("content");
        movie5.setDirector("director");
        movie5.setDuration(0.0f);
        movie5.setFromDate(LocalDate.of(2020, 1, 1));
        movie5.setMovieCompany("movieCompany");
        movie5.setToDate(LocalDate.of(2020, 1, 1));
        movie5.setVersion("version");
        movie5.setNameEng("nameEng");
        schedule1.setScheduleMovies(Arrays.asList(new ScheduleMovie(new ScheduleMovieId(movie5, new Schedule()))));
        final Movie movie6 = new Movie();
        movie6.setId(0);
        movie6.setActor("actor");
        movie6.setContent("content");
        movie6.setDirector("director");
        movie6.setDuration(0.0f);
        movie6.setFromDate(LocalDate.of(2020, 1, 1));
        movie6.setMovieCompany("movieCompany");
        movie6.setToDate(LocalDate.of(2020, 1, 1));
        movie6.setVersion("version");
        movie6.setNameEng("nameEng");
        schedule1.setSchedules(Arrays.asList(new ScheduleSeat(0, movie6, new Schedule(), 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket()))));
        ticket1.setScheduleSeat(new ScheduleSeat(0, movie4, schedule1, 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(new Ticket())));
        final Invoice invoice2 = new Invoice();
        invoice2.setId(0);
        final Account account3 = new Account();
        account3.setId(0);
        account3.setAddress("address");
        account3.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account3.setEmail("email");
        account3.setFullName("fullName");
        account3.setGender("gender");
        account3.setIdentityCard("identityCard");
        account3.setImage("image");
        account3.setUsername("username");
        account3.setPassword("password");
        invoice2.setAccount(account3);
        invoice2.setAddScore(0);
        invoice2.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice2.setMovieName("movieName");
        invoice2.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice2.setScheduleShowTime("scheduleShowTime");
        invoice2.setStatus((byte) 0b0);
        invoice2.setTotalMoney(new BigDecimal("0.00"));
        invoice2.setUserScore(0);
        ticket1.setInvoice(invoice2);
        ticket1.setPrice(new BigDecimal("0.00"));
        final ScheduleSeat scheduleSeat = new ScheduleSeat(0, movie, schedule, 0, "seatColumn", 0, (byte) 0b0, 0, LocalDate.of(2020, 1, 1), new BigDecimal("0.00"), Arrays.asList(ticket1));
        when(mockScheduleSeatConverter.convertToEntity(any(ScheduleSeatDTO.class))).thenReturn(scheduleSeat);

        // Configure InvoiceRepository.save(...).
        final Invoice invoice3 = new Invoice();
        invoice3.setId(0);
        final Account account4 = new Account();
        account4.setId(0);
        account4.setAddress("address");
        account4.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account4.setEmail("email");
        account4.setFullName("fullName");
        account4.setGender("gender");
        account4.setIdentityCard("identityCard");
        account4.setImage("image");
        account4.setUsername("username");
        account4.setPassword("password");
        invoice3.setAccount(account4);
        invoice3.setAddScore(0);
        invoice3.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice3.setMovieName("movieName");
        invoice3.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice3.setScheduleShowTime("scheduleShowTime");
        invoice3.setStatus((byte) 0b0);
        invoice3.setTotalMoney(new BigDecimal("0.00"));
        invoice3.setUserScore(0);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice3);

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO5 = new SeatDTO();
        seatDTO5.setSeatId(0);
        seatDTO5.setSeatColumn("seatColumn");
        seatDTO5.setSeatRow(0);
        seatDTO5.setStatus((byte) 0b0);
        seatDTO5.setSeatType(0);
        seatDTO5.setSeatPrice(new BigDecimal("0.00"));
        seatDTO5.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO6 = new SeatDTO();
        seatDTO6.setSeatId(0);
        seatDTO6.setSeatColumn("seatColumn");
        seatDTO6.setSeatRow(0);
        seatDTO6.setStatus((byte) 0b0);
        seatDTO6.setSeatType(0);
        seatDTO6.setSeatPrice(new BigDecimal("0.00"));
        seatDTO6.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO7 = new SeatDTO();
        seatDTO7.setSeatId(0);
        seatDTO7.setSeatColumn("seatColumn");
        seatDTO7.setSeatRow(0);
        seatDTO7.setStatus((byte) 0b0);
        seatDTO7.setSeatType(0);
        seatDTO7.setSeatPrice(new BigDecimal("0.00"));
        seatDTO7.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO8 = new SeatDTO();
        seatDTO8.setSeatId(0);
        seatDTO8.setSeatColumn("seatColumn");
        seatDTO8.setSeatRow(0);
        seatDTO8.setStatus((byte) 0b0);
        seatDTO8.setSeatType(0);
        seatDTO8.setSeatPrice(new BigDecimal("0.00"));
        seatDTO8.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO9 = new SeatDTO();
        seatDTO9.setSeatId(0);
        seatDTO9.setSeatColumn("seatColumn");
        seatDTO9.setSeatRow(0);
        seatDTO9.setStatus((byte) 0b0);
        seatDTO9.setSeatType(0);
        seatDTO9.setSeatPrice(new BigDecimal("0.00"));
        seatDTO9.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO1 = new InvoiceDTO();
        invoiceDTO.setCustomerPhone("0374550289");
        invoiceDTO.setTotalMoney(BigDecimal.valueOf(10000));
        invoiceDTO.setMovieName("Thanh Cong se den thoai");
        invoiceDTO.setCustomerIdentityCard("061106221");
        invoiceDTO.setScheduleShow(LocalDate.of(2020,10,10));
        invoiceDTO.setScheduleSeats(null);
        invoiceDTO.setScheduleShowTime("9:00");
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO1);
        // Run the test
        assertThrows(IllegalArgumentException.class,()->invoiceServiceImplUnderTest.insertInvoice(invoiceDTO));
    }
    /**
     * Author: DaoTQ1
     * Description: This function find All record in database, return List of Invoices
     * Result: The test is pass. The function return list of invoices
     * @throws Exception
     */
    @Test
    void testFindAll() {
        // Setup

        // Configure InvoiceRepository.findAll(...).
        final Invoice invoice = new Invoice();
        invoice.setId(0);
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        invoice.setAccount(account);
        invoice.setAddScore(0);
        invoice.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice.setMovieName("movieName");
        invoice.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice.setScheduleShowTime("scheduleShowTime");
        invoice.setStatus((byte) 0b0);
        invoice.setTotalMoney(new BigDecimal("0.00"));
        invoice.setUserScore(0);
        final List<Invoice> invoices = Arrays.asList(invoice);
        when(mockInvoiceRepository.findAll()).thenReturn(invoices);

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        // Run the test
        final List<InvoiceDTO> result = invoiceServiceImplUnderTest.findAll();
        // Verify the results
        System.out.println(result.toString());
    }
    /**
     * Author: DaoTQ1
     * Description: This function find All record in database, but List of Invoices is empty
     * Result: The test is pass. The function return list empty Invoices
     * @throws Exception
     */
    @Test
    void testFindAll_InvoiceRepositoryReturnsNoItems() {
        // Setup
        when(mockInvoiceRepository.findAll()).thenReturn(Collections.emptyList());

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        // Run the test
        final List<InvoiceDTO> result = invoiceServiceImplUnderTest.findAll();
        // Verify the results
        assertEquals(result, new ArrayList<>());
    }
    /**
     * Author: DaoTQ1
     * Description: This function find Invoice record existed database with ID is 1.
     * Result: The test is Pass, found InvoiceId. Return invoiceDTO object
     */
    @Test
    void testFindById() {
        // Setup

        // Configure InvoiceRepository.findById(...).
        final Invoice invoice1 = new Invoice();
        invoice1.setId(0);
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        invoice1.setAccount(account);
        invoice1.setAddScore(0);
        invoice1.setBookingDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        invoice1.setMovieName("movieName");
        invoice1.setScheduleShow(LocalDate.of(2020, 1, 1));
        invoice1.setScheduleShowTime("scheduleShowTime");
        invoice1.setStatus((byte) 0b0);
        invoice1.setTotalMoney(new BigDecimal("0.00"));
        invoice1.setUserScore(0);
        final Optional<Invoice> invoice = Optional.of(invoice1);
        when(mockInvoiceRepository.findById(0)).thenReturn(invoice);

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        // Run the test
        final InvoiceDTO result = invoiceServiceImplUnderTest.findById(0);
        System.out.println(result);
        // Verify the results
        assertEquals(result, invoiceDTO);
    }
    /**
     * Author: DaoTQ1
     * Description: This function find Invoice record doesn't existed database with ID is 100.
     * Result: The test is Failed, the function throws NotFoundException. throws Not Found Exception
     */
    @Test
    void testFindById_InvoiceRepositoryReturnsAbsent() {
        // Setup
        when(mockInvoiceRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InvoiceConverter.convertToDTO(...).
        final SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(0);
        seatDTO.setSeatColumn("seatColumn");
        seatDTO.setSeatRow(0);
        seatDTO.setStatus((byte) 0b0);
        seatDTO.setSeatType(0);
        seatDTO.setSeatPrice(new BigDecimal("0.00"));
        seatDTO.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setSeatId(0);
        seatDTO1.setSeatColumn("seatColumn");
        seatDTO1.setSeatRow(0);
        seatDTO1.setStatus((byte) 0b0);
        seatDTO1.setSeatType(0);
        seatDTO1.setSeatPrice(new BigDecimal("0.00"));
        seatDTO1.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO2 = new SeatDTO();
        seatDTO2.setSeatId(0);
        seatDTO2.setSeatColumn("seatColumn");
        seatDTO2.setSeatRow(0);
        seatDTO2.setStatus((byte) 0b0);
        seatDTO2.setSeatType(0);
        seatDTO2.setSeatPrice(new BigDecimal("0.00"));
        seatDTO2.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO3 = new SeatDTO();
        seatDTO3.setSeatId(0);
        seatDTO3.setSeatColumn("seatColumn");
        seatDTO3.setSeatRow(0);
        seatDTO3.setStatus((byte) 0b0);
        seatDTO3.setSeatType(0);
        seatDTO3.setSeatPrice(new BigDecimal("0.00"));
        seatDTO3.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final SeatDTO seatDTO4 = new SeatDTO();
        seatDTO4.setSeatId(0);
        seatDTO4.setSeatColumn("seatColumn");
        seatDTO4.setSeatRow(0);
        seatDTO4.setStatus((byte) 0b0);
        seatDTO4.setSeatType(0);
        seatDTO4.setSeatPrice(new BigDecimal("0.00"));
        seatDTO4.setCinemaRoom(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(new MovieDTO(null, 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, null))))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(null, null, new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList()), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList()))))))), Arrays.asList(new SeatDTO())));
        final InvoiceDTO invoiceDTO = new InvoiceDTO(0, new AccountDTO(0, "email", "fullName", "gender", "identityCard", "image", "username", "password", "phoneNumber", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO(0, 0, null))), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "movieName", LocalDate.of(2020, 1, 1), "scheduleShowTime", (byte) 0b0, new BigDecimal("0.00"), 0, "seat", Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, null, 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList(new ScheduleSeatDTO(0, null, new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(null, null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()))), new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO1)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList()), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, null)), Arrays.asList(), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList()), null)), "customerName", "customerPhone", "customerIdentityCard", "memberId", new PromotionDTO(0, "detail", new BigDecimal("0.00"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "image", new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), "title", Arrays.asList()), Arrays.asList(new ScheduleSeatDTO(0, new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO2)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), new ScheduleDTO(0, "time", Arrays.asList(new ScheduleMovieDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO3)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(new TicketDTO(null, null, null)), Arrays.asList()), null)), Arrays.asList()), 0, "seatColumn", 0, (byte) 0b0, 0, new BigDecimal("0.00"), Arrays.asList(new TicketDTO(new MovieDTO(new CinemaRoomDTO(0, "roomName", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, Arrays.asList(), Arrays.asList(seatDTO4)), 0, "actor", "content", "director", 0.0f, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "movieCompany", "version", "nameEng", "nameVN", "avatar", "image", Arrays.asList(new ScheduleMovieDTO(null, new ScheduleDTO(0, "time", Arrays.asList(), Arrays.asList()))), Arrays.asList(new TypeMovieDTO(null, new TypeDTO(0, "name", Arrays.asList()))), Arrays.asList(), Arrays.asList()), null, null)))));
        when(mockInvoiceConverter.convertToDTO(any(Invoice.class))).thenReturn(invoiceDTO);

        // Run the test

        // Verify the results
        assertThrows(NotFoundException.class, () -> invoiceServiceImplUnderTest.findById(0));
    }
}
