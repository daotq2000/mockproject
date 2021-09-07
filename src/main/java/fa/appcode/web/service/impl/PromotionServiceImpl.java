package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.DateRangeException;
import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.entities.Promotion;
import fa.appcode.web.repository.PromotionRepository;
import fa.appcode.web.service.PromotionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public Map<String, Object> paging(int page, int size, String search, String field, String order) {
        return null;
    }

    @Override
    public Promotion save(Promotion promotion) {
        if (promotion.getStartTime().after(promotion.getEndTime())) {
            throw new DateRangeException(CONSTANT.DATE_RANGE_NOT_VALID);
        }
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion update(Promotion promotion, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (getById(id) == null) {
            throw new NotFoundException(CONSTANT.PROMOTION_NOT_FOUND);
        }
        promotionRepository.deleteById(id);
        return true;
    }

    @Override
    public Promotion findById(Integer id) {
        return null;
    }

    @Override
    public Page<Promotion> getByPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return promotionRepository.findAll(pageable);
    }

    @Override
    public Page<Promotion> getByPageAndTitle(Integer pageNumber, Integer pageSize, String title) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (title == null) title = "";
        return promotionRepository.findByTitleLike(title, pageable);
    }

    @Override
    public Promotion getById(Integer id) {
        Optional<Promotion> optional = promotionRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Promotion save(Promotion promotion, MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                //throw not valid file
            }
            promotion.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            return save(promotion);
        } catch (NullPointerException | IOException e) {
            logger.error(e);
        }
        return null;
    }
}
