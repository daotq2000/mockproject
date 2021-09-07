package fa.appcode.web.service.impl;

import fa.appcode.web.converter.AccountConverter;
import fa.appcode.web.dto.AccountDTO;
import fa.appcode.web.entities.Account;
import fa.appcode.web.entities.Member;
import fa.appcode.web.entities.Role;
import fa.appcode.web.repository.AccountRepository;
import fa.appcode.web.repository.MemberRepository;
import fa.appcode.web.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
    @Mock
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Mock
    private AccountRepository mockAccountRepository;
    @Mock
    private AccountConverter mockAccountConverter;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private MemberRepository mockMemberRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImplUnderTest;

    @Test
    public void testFindById() throws Exception {
        // Setup


        // Configure AccountRepository.findById(...).
        final Account account1 = new Account();
        account1.setId(1);
        account1.setAddress("address");
        account1.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account1.setEmail("email");
        account1.setFullName("fullName");
        account1.setGender("gender");
        account1.setIdentityCard("identityCard");
        account1.setImage("image");
        account1.setUsername("username");
        account1.setPassword("password");
        final Optional<Account> account = Optional.of(account1);
        when(mockAccountRepository.findById(1)).thenReturn(account);

        final AccountDTO accountDTO = mockAccountConverter.convertToDTO(account.get());


        // Run the test
        final AccountDTO result = accountServiceImplUnderTest.findById(1);

        // Verify the results
        assertEquals(accountDTO, result);
    }

    @Test
    public void testFindById_ThrowsRuntimeException() {
        // Setup

        // Configure AccountRepository.findById(...).
        final Account account1 = new Account();
        account1.setId(0);
        account1.setAddress("address");
        account1.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account1.setEmail("email");
        account1.setFullName("fullName");
        account1.setGender("gender");
        account1.setIdentityCard("identityCard");
        account1.setImage("image");
        account1.setUsername("username");
        account1.setPassword("password");
        final Optional<Account> account = Optional.of(account1);
        when(mockAccountRepository.findById(0)).thenReturn(account);

        when(mockAccountConverter.convertToDTO(new Account())).thenReturn(new AccountDTO());

        // Run the test
        assertThrows(RuntimeException.class, () -> accountServiceImplUnderTest.findById(1));
    }

    @Test
    public void testFindAll() throws Exception {
        // Setup
        // Configure AccountRepository.findAll(...).
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        final List<Account> list = Arrays.asList(account);
        when(mockAccountRepository.findAll()).thenReturn(list);

        AccountDTO accountDTO = mockAccountConverter.convertToDTO(account);
        final List<AccountDTO> expectedResult = new ArrayList<>();
        expectedResult.add(accountDTO);

        // Run the test
        final List<AccountDTO> result = accountServiceImplUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }


    @Test
    public void testFindAll_AccountRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockAccountRepository.findAll()).thenReturn(Collections.emptyList());
        when(mockAccountConverter.convertToDTO(new Account())).thenReturn(new AccountDTO());

        // Run the test
        final List<AccountDTO> result = accountServiceImplUnderTest.findAll();

        // Verify the results
        assertEquals(new ArrayList<>(), result);
    }

//    @Test
//    public void testUpdate1() throws Exception {
//        // Setup
//
//        final AccountDTO expectedResult = new AccountDTO();
//
//        // Configure AccountConverter.convertToEntity2(...).
//        final Account account = new Account();
//        account.setId(0);
//        account.setAddress("address");
//        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
//        account.setEmail("email");
//        account.setFullName("fullName");
//        account.setGender("gender");
//        account.setIdentityCard("identityCard");
//        account.setImage("image");
//        account.setUsername("username");
//        account.setPassword(encoder.encode("123456"));
//        when(mockAccountConverter.convertToEntity2(new AccountDTO())).thenReturn(account);
//
//
//
//        // Configure AccountRepository.save(...).
//        final Account account1 = new Account();
//        account1.setId(0);
//        account1.setAddress("address");
//        account1.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
//        account1.setEmail("email");
//        account1.setFullName("fullName");
//        account1.setGender("gender");
//        account1.setIdentityCard("identityCard");
//        account1.setImage("image");
//        account1.setUsername("username");
//        account1.setPassword(encoder.encode("123456"));
//        when(mockAccountRepository.save(new Account())).thenReturn(account1);
//
//        final AccountDTO accountDTO = mockAccountConverter.convertToDTO(account);
//
//        when(mockAccountConverter.convertToDTO(account1)).thenReturn(new AccountDTO());
//        encoder.encode("123456");
//        // Run the test
//        final AccountDTO result = accountServiceImplUnderTest.update(accountDTO);
//
//        // Verify the results
//        assertEquals(expectedResult, result);
//    }


    @Test
    public void testSearch() throws Exception {
        // Setup
        // Configure AccountRepository.search(...).
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        final List<Account> list = Arrays.asList(account);
        when(mockAccountRepository.search("key")).thenReturn(list);

        AccountDTO accountDTO = mockAccountConverter.convertToDTO(account);
        final List<AccountDTO> expectedResult = new ArrayList<>();
        expectedResult.add(accountDTO);

        // Run the test
        final List<AccountDTO> result = accountServiceImplUnderTest.search("key");

        // Verify the results
        assertEquals(expectedResult, result);
    }


    @Test
    public void testSearch_AccountRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockAccountRepository.search("key")).thenReturn(Collections.emptyList());
        when(mockAccountConverter.convertToDTO(new Account())).thenReturn(new AccountDTO());

        // Run the test
        final List<AccountDTO> result = accountServiceImplUnderTest.search("key");

        // Verify the results
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testSave() throws Exception {
        // Setup
        final AccountDTO accountDTO = new AccountDTO();


        // Configure AccountConverter.convertToEntity2(...).
        final Account account = new Account();
        account.setId(0);
        account.setAddress("address");
        account.setDateOfBirth(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        account.setEmail("email");
        account.setFullName("fullName");
        account.setGender("gender");
        account.setIdentityCard("identityCard");
        account.setImage("image");
        account.setUsername("username");
        account.setPassword("password");
        when(mockAccountConverter.convertToEntity2(new AccountDTO())).thenReturn(account);

        // Configure RoleRepository.getOne(...).
        final Role role = new Role();
        role.setId(0);
        role.setRoleName("roleName");
        role.setAccounts(Arrays.asList(account));
        when(mockRoleRepository.getOne(0)).thenReturn(role);

        // Configure AccountRepository.save(...).
        when(mockAccountRepository.save(new Account())).thenReturn(account);

        // Configure MemberRepository.save(...).
        final Member member = new Member();
        member.setMemberId(0);
        member.setScore(0);
        member.setAccount(account);
        when(mockMemberRepository.save(new Member())).thenReturn(member);

        final AccountDTO expectedResult = mockAccountConverter.convertToDTO(account);
        when(mockAccountConverter.convertToDTO(new Account())).thenReturn(new AccountDTO());

        // Run the test
        final AccountDTO result = accountServiceImplUnderTest.save(accountDTO);

        // Verify the results
        assertEquals(expectedResult, result);
    }


}
