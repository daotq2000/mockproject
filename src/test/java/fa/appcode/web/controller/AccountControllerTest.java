package fa.appcode.web.controller;
import fa.appcode.web.dto.AccountDTO;
import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.dto.RoleDTO;
import fa.appcode.web.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService mockAccountService;

    @Test
    public void testRegisterAccount() throws Exception {
        // Setup
        // Configure AccountService.save(...).
        final MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(0);
        memberDTO.setScore(0);
        memberDTO.setAccount(new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO())));
        final AccountDTO accountDTO = new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(memberDTO));
        when(mockAccountService.save(any(AccountDTO.class))).thenReturn(accountDTO);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/register-account")
                .param("accountDTO", "accountDTO")
//                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse(); //and expected
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        //assertEquals("expectedResponse", response.getContentAsString());
    }
    @Test
    public void testShowAccount() throws Exception {
        // Setup
        // Configure AccountService.findById(...).
        final MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(0);
        memberDTO.setScore(0);
        memberDTO.setAccount(new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO())));
        final AccountDTO accountDTO = new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(memberDTO));
        when(mockAccountService.findById(0)).thenReturn(accountDTO);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/account-detail/{id}", 0)
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("expectedResponse", response.getContentAsString());
    }
    @Test
    public void testUpdateAccount() throws Exception {
        // Setup
        // Configure AccountService.update(...).
        final MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(0);
        memberDTO.setScore(0);
        memberDTO.setAccount(new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(new MemberDTO())));
        final AccountDTO accountDTO = new AccountDTO(0, "email", "fullName", "gender", "address", "identityCard", "image", "username", "password", "phoneNumber", "dateOfBirth", LocalDate.of(2020, 1, 1), new RoleDTO(0, "roleName", Arrays.asList()), (byte) 0b0, Arrays.asList(memberDTO));
        when(mockAccountService.update(any(AccountDTO.class))).thenReturn(accountDTO);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/update-account")
                .param("accountDTO", "accountDTO")
//                .with(csrf())
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();
        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        // assertEquals("expectedResponse", response.getContentAsString());
    }
}