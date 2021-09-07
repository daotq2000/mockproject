package fa.appcode.web.converter;

import fa.appcode.web.dto.PromotionDTO;
import fa.appcode.web.entities.Promotion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter {
    ModelMapper modelMapper = new ModelMapper();

    public PromotionDTO convertToDTO(Promotion promotion) {
        PromotionDTO promotionDTO = modelMapper.map(promotion, PromotionDTO.class);
        return promotionDTO;
    }

    public Promotion convertToEntity(PromotionDTO dto) {
        Promotion promotion = modelMapper.map(dto, Promotion.class);
        return promotion;
    }
}
