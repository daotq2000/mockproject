package fa.appcode.web.controller;

import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.repository.ScheduleSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewResolveController {
    @Autowired private ScheduleSeatRepository scheduleSeatRepository;
    @GetMapping("/")
    public String loginPage(){
        return "index";
    }
    @GetMapping("/movieManagement")
    public String admin(){
        return "dashboard";
    }
    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
//AnonymousAuthenticationToken [Principal=anonymousUser, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[ROLE_ANONYMOUS]]
}
