package fa.appcode.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class InvoiceDTO {
    private Integer id;
    private AccountDTO account;
    private Integer addScore;
    private Date bookingDate;
    @NotNull(message = "Movie can not be null")
    private String movieName;
    private LocalDate scheduleShow;
    private String scheduleShowTime;
    private byte status;
    private BigDecimal totalMoney;
    private Integer userScore;
    private String seat;
    private List<TicketDTO> tickets;
    private String customerName;
    private String customerPhone;
    private String customerIdentityCard;
    private String memberId;
    private PromotionDTO promotion;
    @NotNull(message = "ScheduleSeats can not be null")
    private List<ScheduleSeatDTO> scheduleSeats;
}
