package fa.appcode.web.repository;

import fa.appcode.web.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query(value = "Select m.* from account c inner join member m on c.id = m.account_id  " +
            " where c.identity_card =:search or m.member_id  =:search",nativeQuery = true)
     Member searchMember(String search);
}
