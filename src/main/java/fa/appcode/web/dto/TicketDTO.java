package fa.appcode.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode


public class TicketDTO {
    private MovieDTO movie;
    private ScheduleSeatDTO scheduleSeat;
    private InvoiceDTO invoice;

}
