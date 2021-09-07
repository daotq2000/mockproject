package fa.appcode.web.service;

import fa.appcode.web.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PromotionService extends BaseService<Promotion, Integer> {
    Page<Promotion> getByPage(Integer pageNumber, Integer pageSize);

    Page<Promotion> getByPageAndTitle(Integer pageNumber, Integer pageSize, String keyword);

    Promotion getById(Integer id);

    Promotion save(Promotion promotion, MultipartFile file);
}
