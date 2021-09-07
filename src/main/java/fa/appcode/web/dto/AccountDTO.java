package fa.appcode.web.dto;

import fa.appcode.web.entities.Member;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountDTO {
    private Integer id;
    @NotNull(message = "Can not null")
    private String email;
    private String fullName;
    private String gender;
    private String identityCard;
    private String image;
    private String username;
    private String password;
    private String phoneNumber;
    private Date dateOfBirth;
    private LocalDate registerDate;
    private RoleDTO role;
    private byte status;
    private List<MemberDTO> members;
    private String address;
    private String birthDay;


    public <T> AccountDTO(int i, String email, String fullName, String gender, String identityCard, String image, String username, String password, String phoneNumber, Date time, LocalDate of, RoleDTO roleName, byte b, List<MemberDTO> asList) {
    this.id = i;
    this.email = email;
    this.fullName = fullName;
    this.gender = gender;
    this.identityCard = identityCard;
    this.image = image;
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = time;
    this.role = roleName;
    this.status = b;
    this.registerDate = of;
    this.members = asList;
    }

    public <T> AccountDTO(int i, String email, String fullName, String gender, String address, String identityCard, String image, String username, String password, String phoneNumber, String dateOfBirth, LocalDate of, RoleDTO roleName, byte b, List<T> asList) {
    }
}
