package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SCHEDULE")
@EqualsAndHashCode

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TIME")
    private String time;
    @OneToMany(mappedBy = "scheduleMovieId.schedule")
    private List<ScheduleMovie> scheduleMovies;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "schedule")
    private List<ScheduleSeat> schedules;
}
