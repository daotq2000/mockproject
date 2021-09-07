package fa.appcode.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class DateMovieDTO {
    private ShowDateDTO date;
    private MovieDTO movie;
}
