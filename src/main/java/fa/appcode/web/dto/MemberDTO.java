package fa.appcode.web.dto;

import fa.appcode.web.entities.Account;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class MemberDTO {
    private Integer memberId;
    private Integer score;
    private AccountDTO account;
}
