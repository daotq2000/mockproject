package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "TICKET")
@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "SCHEDULE_SEAT_ID")
    private ScheduleSeat scheduleSeat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;
    @Column(name = "PRICE")
    private BigDecimal price;

}
