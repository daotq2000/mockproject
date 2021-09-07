package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class DateMovieId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOWDATE_ID")
    private ShowDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
}
