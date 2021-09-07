package fa.appcode.web.controller;

import fa.appcode.web.converter.PromotionConverter;
import fa.appcode.web.dto.PromotionDTO;
import fa.appcode.web.entities.Promotion;
import fa.appcode.web.service.PromotionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/promotion")
@CrossOrigin
public class PromotionController {

    final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    PromotionService promotionService;

    PromotionConverter promotionConverter = new PromotionConverter();

    @GetMapping({"/list", "/"})
    @ResponseStatus(HttpStatus.OK)
    public Page<PromotionDTO> list(@RequestParam(value = "page", required = false) Integer pageNum,
                                   @RequestParam(value = "size", required = false) Integer pageSize,
                                   @RequestParam(value = "title", required = false) String title
    ) {
        logger.debug("[GET]: List promotion");
        Page<Promotion> pageEntity = promotionService.getByPageAndTitle(pageNum, pageSize, title);
        Page<PromotionDTO> pageDTO = pageEntity.map(promotion -> promotionConverter.convertToDTO(promotion));
        return pageDTO;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PromotionDTO get(@PathVariable("id") Integer id) {
        Promotion promotion = promotionService.getById(id);
        PromotionDTO promotionDTO = promotionConverter.convertToDTO(promotion);
        return promotionDTO;
    }

    @PostMapping({"/save"})
    public ResponseEntity<Promotion> save(@Valid @RequestBody Promotion promotion) throws ParseException {
        logger.debug("[SAVE]: Save promotion");
        promotion = promotionService.save(promotion);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }
//    @PostMapping({"/save"})
//    public ResponseEntity<Promotion> save(@Valid @RequestBody Promotion promotion, @RequestParam(value = "file", required = false) MultipartFile file) {
//        logger.debug("[SAVE]: Save promotion");
//        promotion = promotionService.save(promotion, file);
//        return new ResponseEntity<>(promotion, HttpStatus.OK);
//    }

    @DeleteMapping({"/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        logger.debug("[DELETE]: Delete promotion");
        promotionService.delete(id);
    }
}
