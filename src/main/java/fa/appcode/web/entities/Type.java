package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TYPE")
@EqualsAndHashCode

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME",columnDefinition = "NVARCHAR(200)")
    private String name;
    @OneToMany(mappedBy = "typeMovieId.type")
    private List<TypeMovie> typeMovies;
}
