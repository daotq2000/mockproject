package fa.appcode.web.service;

import fa.appcode.web.dto.TypeDTO;

import java.util.List;

public interface TypeService extends BaseService<TypeDTO, Integer> {
    List<TypeDTO> findAll();
}
