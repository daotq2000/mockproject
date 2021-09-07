package fa.appcode.web.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
@ToString

public class ScheduleMovieId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    public ScheduleMovieId(Movie movie, Schedule schedule) {
        this.movie = movie;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "ScheduleMovieId{" +
                "movie=" + movie.getId() +
                ", schedule=" + schedule.getId() +
                '}';
    }
}
