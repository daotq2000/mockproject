package fa.appcode.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class TypeMovieDTO {
    private MovieDTO movie;
    private TypeDTO type;
}
