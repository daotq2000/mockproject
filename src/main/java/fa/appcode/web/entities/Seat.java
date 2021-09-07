package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "SEAT")
@EqualsAndHashCode

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;
    @Column(name = "SEAT_COLUMN")
    private String seatColumn;
    @Column(name = "SEAT_ROW")
    private Integer seatRow;
    @Column(name = "STATUS")
    private Byte status;
    @Column(name = "SEAT_TYPE")
    private Integer seatType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CINEMAROOM_ID")
    private CinemaRoom cinemaRoom;
    @Column(name = "SEAT_PRICE")
    private BigDecimal seatPrice;

    public Seat() {
    }



    public Seat(String seatColumn, Integer seatRow, Byte status, Integer seatType, BigDecimal seatPrice) {
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.status = status;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", seatColumn='" + seatColumn + '\'' +
                ", seatRow=" + seatRow +
                ", status=" + status +
                ", seatType=" + seatType +
                ", seatPrice=" + seatPrice +
                '}';
    }
}
