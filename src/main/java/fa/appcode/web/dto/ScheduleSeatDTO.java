package fa.appcode.web.dto;

import fa.appcode.web.entities.CinemaRoom;
import fa.appcode.web.entities.Movie;
import fa.appcode.web.entities.Schedule;
import fa.appcode.web.entities.Ticket;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class ScheduleSeatDTO {
    private Integer scheduleSeatId;
    @NotNull(message = "Movie cannot be null")
    private MovieDTO movie;
    @NotNull(message = "Schedule cannot be null")
    private ScheduleDTO schedule;
    @NotNull(message = "Seat Id cannot be null")
    private Integer seatId;
    @NotNull(message = "Seat Column cannot be null")
    private String seatColumn;
    @NotNull(message = "Seat Row cannot be null")
    private Integer seatRow;
    @NotNull(message = "Seat Status cannot be null")
    private Byte status;
    @NotNull(message = "Seat Type cannot be null")
    private Integer seatType;
    @NotNull(message = "Seat Price cannot be null")
    private BigDecimal seatPrice;
    private List<TicketDTO> tickets;
}
