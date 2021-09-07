package fa.appcode.web.converter;

import fa.appcode.web.dto.MemberDTO;
import fa.appcode.web.entities.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired AccountConverter accountConverter;
    public MemberDTO convertToMemberDTO(Member member){
        MemberDTO memberDTO =  new MemberDTO();
        if(member.getAccount() != null){
            memberDTO.setAccount(accountConverter.convertForMemberDTO(member.getAccount()));
        }
        memberDTO.setScore(member.getScore());
        memberDTO.setMemberId(member.getMemberId());
        return memberDTO;
    }
    public Member convertToMemberEntity(MemberDTO memberDTO){
        return modelMapper.map(memberDTO,Member.class);
    }
}
