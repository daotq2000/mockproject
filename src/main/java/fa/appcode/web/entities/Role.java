package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "ROLE")
@Entity
@NoArgsConstructor
@EqualsAndHashCode

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    private List<Account> accounts;
}
