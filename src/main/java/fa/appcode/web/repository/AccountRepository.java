package fa.appcode.web.repository;

import fa.appcode.web.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query(value = "select c from Account c where c.username=:account")
    Account getByUsername(String account);
    @Query(value ="select c from Account c where c.username like %:key% or c.email  like %:key% or c.address  like %:key% or c.phoneNumber  like %:key% or c.identityCard  like %:key%" )
    List<Account> search(String key);
}
