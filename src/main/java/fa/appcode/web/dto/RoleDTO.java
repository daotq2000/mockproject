package fa.appcode.web.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class RoleDTO {
    private Integer id;
    private String roleName;
    private List<AccountDTO> accounts;
}
