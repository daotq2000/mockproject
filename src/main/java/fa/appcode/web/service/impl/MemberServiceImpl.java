package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.converter.MemberConverter;
import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.entities.Member;
import fa.appcode.web.repository.MemberRepository;
import fa.appcode.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberConverter memberConverter;
    @Override
    public MemberDTO findMember(String memberId) {
        Member member = memberRepository.searchMember(memberId);
        if(member != null){
            return memberConverter.convertToMemberDTO(member);
        }
        throw new NotFoundException("Member Not Found");
    }
}
