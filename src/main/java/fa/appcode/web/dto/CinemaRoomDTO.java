package fa.appcode.web.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class CinemaRoomDTO {

    private Integer id;
    private String roomName;
    private BigDecimal vipPrice;
    private BigDecimal normalPrice;
    private Integer seatQuantity;
    private List<MovieDTO> movies;
    private List<SeatDTO> seats;


    public CinemaRoomDTO(String room1, BigDecimal valueOf, BigDecimal valueOf1, int i) {
        this.id = i;
        this.roomName = room1;
        this.vipPrice = valueOf;
        this.normalPrice = valueOf1;
    }

    public CinemaRoomDTO(int i, String room1, BigDecimal valueOf, BigDecimal valueOf1, int i1) {
        this.id = i;
        this.roomName = room1;
        this.vipPrice = valueOf;
        this.normalPrice = valueOf1;

    }
}
