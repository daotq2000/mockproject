package fa.appcode.web.converter;

import fa.appcode.web.dto.AccountDTO;
import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.entities.Account;
import fa.appcode.web.entities.Member;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AccountConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public AccountDTO convertForMemberDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setGender(account.getGender());
        accountDTO.setId(account.getId());
        accountDTO.setIdentityCard(account.getIdentityCard());
        accountDTO.setImage(account.getImage());
        accountDTO.setPassword(null);
        accountDTO.setUsername(account.getUsername());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setRegisterDate(account.getRegisterDate());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        return accountDTO;
    }


    public Account convertToEntity(AccountDTO accountDTO){
        Account account = modelMapper.map(accountDTO, Account.class);
        return account;
    }
    public AccountDTO convertToDTO(Account account)  {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setGender(account.getGender());
        accountDTO.setIdentityCard(account.getIdentityCard());
        accountDTO.setImage(account.getImage());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setRole(accountDTO.getRole());
        accountDTO.setRegisterDate(account.getRegisterDate());
        accountDTO.setStatus(account.getStatus());
        return accountDTO;
    }
    public Account convertToEntity2(AccountDTO accountDTO){
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setAddress(accountDTO.getAddress());

        account.setEmail(accountDTO.getEmail());
        account.setFullName(accountDTO.getFullName());
        account.setGender(accountDTO.getGender());
        account.setIdentityCard(accountDTO.getIdentityCard());
        account.setImage(accountDTO.getImage());
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setRegisterDate(accountDTO.getRegisterDate());
        return account;
    }
}
