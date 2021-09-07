package fa.appcode.web.converter;

import fa.appcode.web.dto.TypeDTO;
import fa.appcode.web.dto.TypeMovieDTO;
import fa.appcode.web.entities.Type;
import fa.appcode.web.entities.TypeMovie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeMovieConverter {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private TypeConverter typeConverter;
    public TypeMovieDTO convertToDTO(TypeMovie typeMovie){
        TypeMovieDTO typeMovieDTO = new TypeMovieDTO();
        if(typeMovie.getTypeMovieId().getType() != null){
            typeMovieDTO.setType(typeConverter.convertToDTO(typeMovie.getTypeMovieId().getType()));
        }
        return typeMovieDTO;
    }
    public Type convertToEntity(TypeDTO typeDTO){
        return modelMapper.map(typeDTO,Type.class);
    }
}
