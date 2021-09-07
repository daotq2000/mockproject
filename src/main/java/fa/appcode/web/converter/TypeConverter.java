package fa.appcode.web.converter;

import fa.appcode.web.dto.TypeDTO;
import fa.appcode.web.entities.Type;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TypeConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public TypeDTO convertToDTO(Type type){
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setId(type.getId());
        typeDTO.setName(type.getName());
        typeDTO.setTypeMovies(null);
        return typeDTO;
    }
    public Type convertToEntity(TypeDTO typeDTO){
        Type seat = modelMapper.map(typeDTO, Type.class);
        return seat;
    }
}
