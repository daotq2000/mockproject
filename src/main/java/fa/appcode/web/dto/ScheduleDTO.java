package fa.appcode.web.dto;

import fa.appcode.web.entities.ScheduleSeat;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class ScheduleDTO {
    private Integer id;
    private String time;
    private List<ScheduleMovieDTO> scheduleMovies;
    private List<ScheduleSeatDTO> scheduleSeats;

    public ScheduleDTO(Integer id, String time) {
        this.id = id;
        this.time = time;
    }
}
