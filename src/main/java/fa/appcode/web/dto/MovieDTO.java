package fa.appcode.web.dto;
import fa.appcode.web.entities.ScheduleSeat;
import java.util.Objects;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class MovieDTO {
  @NotNull(message = "Please provide a cinema room")
  private CinemaRoomDTO cinemaRoom;
  private Integer id;
  @NotBlank(message = "Please provide actor")
  private String actor;
  @NotBlank(message = "Please provide content")
  private String content;
  @NotBlank(message = "Please provide director")
  private String director;
  @Min(value = 1, message = "Duration should not be less than 1")
  private Float duration;
  @NotNull(message = "Please provide start date")
  private LocalDate fromDate;
  @NotNull(message = "Please provide end date")
  private LocalDate toDate;
  @NotBlank(message = "Please provide movie company")
  private String movieCompany;
  @NotBlank(message = "Please provide version")
  private String version;
  @NotBlank(message = "Please provide an english name")
  private String nameEng;
  @NotBlank(message = "Please provide vietnamese name")
  private String nameVN;
  private String avatar;
  private String image;
  @NotNull(message = "Please provide schedule movie")
  private List<ScheduleMovieDTO> scheduleMovies;
  @NotNull(message = "Please provide type movie")
  private List<TypeMovieDTO> typeMovies;
  private List<TicketDTO> tickets;
  private List<ScheduleSeatDTO> scheduleSeats;
  public MovieDTO(Integer id,
                  @NotBlank(message = "Please provide actor") String actor,
                  @NotBlank(message = "Please provide content") String content,
                  @NotBlank(message = "Please provide director") String director,
                  @Min(value = 1, message = "Duration should not be less than 1") Float duration,
                  @NotNull(message = "Please provide start date") LocalDate fromDate,
                  @NotNull(message = "Please provide end date") LocalDate toDate,
                  @NotBlank(message = "Please provide movie company") String movieCompany,
                  @NotBlank(message = "Please provide version") String version,
                  @NotBlank(message = "Please provide an english name") String nameEng,
                  @NotBlank(message = "Please provide vietnamese name") String nameVN,
                  String avatar, String image) {
    this.id = id;
    this.actor = actor;
    this.content = content;
    this.director = director;
    this.duration = duration;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.movieCompany = movieCompany;
    this.version = version;
    this.nameEng = nameEng;
    this.nameVN = nameVN;
    this.avatar = avatar;
    this.image = image;
  }

    public MovieDTO(
        @NotNull(message = "Please provide a cinema room") CinemaRoomDTO cinemaRoom,
        Integer id,
        @NotBlank(message = "Please provide actor") String actor,
        @NotBlank(message = "Please provide content") String content,
        @NotBlank(message = "Please provide director") String director,
        @Min(value = 1, message = "Duration should not be less than 1") Float duration,
        @NotNull(message = "Please provide start date") LocalDate fromDate,
        @NotNull(message = "Please provide end date") LocalDate toDate,
        @NotBlank(message = "Please provide movie company") String movieCompany,
        @NotBlank(message = "Please provide version") String version,
        @NotBlank(message = "Please provide an english name") String nameEng,
        @NotBlank(message = "Please provide vietnamese name") String nameVN,
        String avatar, String image,
        @NotNull(message = "Please provide schedule movie") List<ScheduleMovieDTO> scheduleMovies,
        @NotNull(message = "Please provide type movie") List<TypeMovieDTO> typeMovies) {
        this.cinemaRoom = cinemaRoom;
        this.id = id;
        this.actor = actor;
        this.content = content;
        this.director = director;
        this.duration = duration;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.movieCompany = movieCompany;
        this.version = version;
        this.nameEng = nameEng;
        this.nameVN = nameVN;
        this.avatar = avatar;
        this.image = image;
        this.scheduleMovies = scheduleMovies;
        this.typeMovies = typeMovies;
    }




    public MovieDTO(String actor) {
      this.actor=actor;
    }

    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieDTO movieDTO = (MovieDTO) o;
    return id.equals(movieDTO.id);
  }
}