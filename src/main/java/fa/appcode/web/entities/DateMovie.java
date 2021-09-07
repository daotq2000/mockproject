package fa.appcode.web.entities;


import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "DATE_MOVIE")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class DateMovie {
    @EmbeddedId
    private DateMovieId dateMovieId;

}
