package fa.appcode.web.service.impl;
import fa.appcode.web.converter.AccountConverter;
import fa.appcode.web.dto.AccountDTO;
import fa.appcode.web.entities.*;
import fa.appcode.web.repository.AccountRepository;
import fa.appcode.web.repository.MemberRepository;
import fa.appcode.web.repository.RoleRepository;
import fa.appcode.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConverter accountConverter;
    @Autowired private RoleRepository roleRepository;
    @Autowired private MemberRepository memberRepository;
    @Override
    public AccountDTO findById(Integer id){
        Optional<Account> optional = accountRepository.findById(id);
        Account account = null;
        if(optional.isPresent()){
            account = optional.get();
        }
        else{
            throw new RuntimeException("Did not find account");
        }
        return accountConverter.convertToDTO(account);
    }
    @Override
    public List<AccountDTO> findAll(){
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account:
                accounts) {
            accountDTOS.add(accountConverter.convertToDTO(account));
        }
        return accountDTOS;
    }
    @Override
    public AccountDTO update(AccountDTO accountDTO){
        Account account = accountConverter.convertToEntity2(accountDTO);
        account.setPassword(encoder.encode(accountDTO.getPassword()));
        return accountConverter.convertToDTO(accountRepository.save(account));
    }

    @Override
    public List<AccountDTO> search(String key){
        List<Account> list = accountRepository.search(key);
        return list.stream().map(account -> accountConverter.convertToDTO(account)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> paging(int page, int size, String search, String field, String order) {
        return null;
    }
    @Override
    public AccountDTO save(AccountDTO accountDTO){
        Account account = accountConverter.convertToEntity2(accountDTO);
        account.setPassword(encoder.encode(accountDTO.getPassword()));
        account.setRegisterDate(LocalDate.now());
        account.setRole(roleRepository.getOne(1));
        Account accountSave= accountRepository.save(account);
        Member member = new Member();
        member.setAccount(account);
        member.setScore(0);
        memberRepository.save(member);
        return accountConverter.convertToDTO(accountSave);
    }
    @Override
    public AccountDTO update(AccountDTO accountDTO, Integer id) {
        return null;
    }
    @Override
    public boolean delete(Integer id) {
        accountRepository.deleteById(id);
        return true;
    }
}