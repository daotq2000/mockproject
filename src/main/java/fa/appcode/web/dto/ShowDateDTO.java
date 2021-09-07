package fa.appcode.web.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class ShowDateDTO {
    private Integer id;
    private LocalDate date;
    private String dateName;
    private List<DateMovieDTO> dateMovies;

}
