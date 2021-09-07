package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "SCHEDULE_MOVIE")
@Entity
@EqualsAndHashCode

public class ScheduleMovie {
    @EmbeddedId
    private ScheduleMovieId scheduleMovieId;

    public ScheduleMovie(ScheduleMovieId scheduleMovieId) {
        this.scheduleMovieId = scheduleMovieId;
    }

    public ScheduleMovie() {
    }
}
