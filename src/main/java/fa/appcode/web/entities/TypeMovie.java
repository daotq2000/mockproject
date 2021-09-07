package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TYPE_MOVIE")
@Entity
@EqualsAndHashCode

public class TypeMovie {
    @EmbeddedId
    private TypeMovieId typeMovieId;

    public TypeMovie(TypeMovieId typeMovieId) {
        this.typeMovieId = typeMovieId;
    }
}
