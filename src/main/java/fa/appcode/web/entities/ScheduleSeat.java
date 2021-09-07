package fa.appcode.web.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "SCHEDULE_SEAT")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class ScheduleSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleSeatId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;
    @Column(name = "SEAT_ID")
    private Integer seatId;
    @Column(name = "SEAT_COLUMN")
    private String seatColumn;
    @Column(name = "SEAT_ROW")
    private Integer seatRow;
    @Column(name = "STATUS")
    private Byte status;
    @Column(name = "SEAT_TYPE")
    private Integer seatType;
    @Column(name = "DATE_BOOKING")
    private LocalDate dateBooking;
    @Column(name = "SEAT_PRICE")
    private BigDecimal seatPrice;
    @OneToMany(mappedBy = "scheduleSeat",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
