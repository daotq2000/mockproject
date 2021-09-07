package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CINEMA_ROOM")
@EqualsAndHashCode

public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ROOM_NAME")
    private String roomName;
    @Column(name = "SEAT_QUANTITY")
    private Integer seatQuantity;
    @OneToMany(mappedBy = "cinemaRoom",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Movie> movies;
    @OneToMany(mappedBy = "cinemaRoom",cascade = CascadeType.ALL)
    private List<Seat> seats;

    public CinemaRoom(Integer id, String roomName, Integer seatQuantity) {
        this.id = id;
        this.roomName = roomName;
        this.seatQuantity = seatQuantity;
    }
}
