package fa.appcode.web.repository;

import fa.appcode.web.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query(value = "select p from Promotion p where p.title like %?1%")
    Page<Promotion> findByTitleLike(String key, Pageable pageable);
}
