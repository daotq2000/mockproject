package fa.appcode.web.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@EqualsAndHashCode

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ADDRESS",columnDefinition = "NVARCHAR(200)")
    private String address;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "EMAIL",unique = true)
    private String email;
    @Column(name = "FULLNAME",columnDefinition = "NVARCHAR(100)")
    private String fullName;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "IDENTITY_CARD",unique = true)
    private String identityCard;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "USERNAME",unique = true)
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "REGISTER_DATE")
    private LocalDate registerDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    @Column(name = "STATUS")
    private byte status;
    @OneToMany(mappedBy = "account")
    private List<Invoice> invoices;
    @OneToMany(mappedBy = "account")
    private List<Member> members;
}
