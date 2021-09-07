package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "MOVIE")
@EqualsAndHashCode

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ACTOR")
    private String actor;
    @Column(name = "CONTENT",columnDefinition = "NVARCHAR(MAX)")
    private String content;
    @Column(name = "DIRECTOR",columnDefinition = "NVARCHAR(200)")
    private String director;
    @Column(name = "DURATION")
    private Float duration;
    @Column(name = "FROM_DATE")
    private LocalDate fromDate;
    @Column(name = "MOVIE_COMPANY",columnDefinition = "NVARCHAR(200)")
    private String movieCompany;
    @Column(name = "TO_DATE")
    private LocalDate toDate;
    @Column(name = "VERSION",columnDefinition = "NVARCHAR(200)")
    private String version;
    @Column(name = "NAME_ENGLISH",columnDefinition = "NVARCHAR(200)")
    private String nameEng;
    @Column(name = "NAME_VN",columnDefinition = "NVARCHAR(200)")
    private String nameVN;
    @Column(name = "AVARTAR")
    private String avatar;
    @Column(name = "IMAGE",columnDefinition = "nvarchar(MAX)")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CINEMA_ROOM_ID")
    private CinemaRoom cinemaRoom;
    @OneToMany(mappedBy = "scheduleMovieId.movie",cascade = CascadeType.ALL)
    private List<ScheduleMovie> scheduleMovies;
    @OneToMany(mappedBy = "typeMovieId.movie",cascade = CascadeType.ALL)
    private List<TypeMovie> typeMovies;
    @OneToMany(mappedBy = "dateMovieId.movie",cascade = CascadeType.ALL)
    private List<DateMovie> dateMovies;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "movie",cascade = CascadeType.ALL)
    private List<ScheduleSeat> scheduleSeats;

    public Movie(Integer id, String actor, String content,
                 String director, Float duration, LocalDate fromDate,
                 String movieCompany, LocalDate toDate, String version,
                 String nameEng, String nameVN, String avatar,
                 String image) {
        this.id = id;
        this.actor = actor;
        this.content = content;
        this.director = director;
        this.duration = duration;
        this.fromDate = fromDate;
        this.movieCompany = movieCompany;
        this.toDate = toDate;
        this.version = version;
        this.nameEng = nameEng;
        this.nameVN = nameVN;
        this.avatar = avatar;
        this.image = image;
    }


    public Movie() {

    }
}
