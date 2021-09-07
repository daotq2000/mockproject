package fa.appcode.web.service.impl;

import fa.appcode.web.converter.TypeConverter;
import fa.appcode.web.dto.TypeDTO;
import fa.appcode.web.entities.Type;
import fa.appcode.web.repository.TypeRepository;
import fa.appcode.web.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private TypeConverter typeConverter;
    @Override
    public List<TypeDTO> findAll() {
        List<Type> types = typeRepository.findAll();;
        return types.stream().map(type->typeConverter.convertToDTO(type)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> paging(int page, int size, String search, String field, String order) {
        return null;
    }

    @Override
    public TypeDTO save(TypeDTO typeDTO) {
        return null;
    }

    @Override
    public TypeDTO update(TypeDTO typeDTO, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public TypeDTO findById(Integer id) {
        return null;
    }
}
