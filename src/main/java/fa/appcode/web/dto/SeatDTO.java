package fa.appcode.web.dto;

import fa.appcode.web.entities.CinemaRoom;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
@EqualsAndHashCode

public class SeatDTO {
    private Integer seatId;
    private String seatColumn;
    private Integer seatRow;
    private Byte status;
    private Integer seatType;
    private BigDecimal seatPrice;
    private CinemaRoomDTO cinemaRoom;

    public SeatDTO() {
    }

    public SeatDTO(String seatColumn, Integer seatRow, Byte status, Integer seatType, BigDecimal seatPrice) {
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.status = status;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
    }
}
