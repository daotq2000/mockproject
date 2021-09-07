package fa.appcode.web.service;

import fa.appcode.web.dto.MemberDTO;

public interface MemberService {
    MemberDTO findMember(String memberId);
}
