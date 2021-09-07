package fa.appcode.web.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class TypeDTO {
    private Integer id;
    private String name;
    private List<TypeMovieDTO> typeMovies;
}
