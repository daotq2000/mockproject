package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Table(name = "SHOW_DATE")
@Entity
@EqualsAndHashCode

public class ShowDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "DATE_NAME",columnDefinition = "NVARCHAR(200)")
    private String dateName;
    @OneToMany(mappedBy = "dateMovieId.date")
    private List<DateMovie> dateMovies;

}
