package fa.appcode.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class ScheduleMovieDTO {
    private MovieDTO movie;
    private ScheduleDTO schedule;
}
