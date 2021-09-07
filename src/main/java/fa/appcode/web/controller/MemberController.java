package fa.appcode.web.controller;

import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> findByIdMember(@PathVariable("memberId") String memberId){
        return new ResponseEntity<>( memberService.findMember(memberId), HttpStatus.OK);
    }
}
