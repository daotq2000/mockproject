package fa.appcode.web.service;

import fa.appcode.web.dto.AccountDTO;

import java.text.ParseException;
import java.util.List;

public interface AccountService extends BaseService<AccountDTO,Integer> {
    AccountDTO findById(Integer id) throws ParseException;
    List<AccountDTO> findAll() throws ParseException;
    AccountDTO update(AccountDTO accountDTO) throws ParseException;
    List<AccountDTO> search(String key) throws ParseException;
}
