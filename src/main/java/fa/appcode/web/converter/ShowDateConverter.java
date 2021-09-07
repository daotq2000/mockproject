package fa.appcode.web.converter;

import fa.appcode.web.dto.ShowDateDTO;
import fa.appcode.web.entities.ShowDate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShowDateConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public ShowDateDTO convertToDTO(ShowDate showDate){
        ShowDateDTO showDateDTO = new ShowDateDTO();
        showDateDTO.setDate(showDate.getDate());
        showDateDTO.setDateName(showDate.getDateName());
        return showDateDTO;
    }
    public ShowDate convertToEntity(ShowDateDTO showDateDTO){
         ShowDate showDate = modelMapper.map(showDateDTO, ShowDate.class);
         return showDate;
    }

}
