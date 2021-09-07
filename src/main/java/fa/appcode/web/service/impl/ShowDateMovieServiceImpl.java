package fa.appcode.web.service.impl;

import fa.appcode.web.converter.DateMovieConverter;
import fa.appcode.web.converter.ShowDateConverter;
import fa.appcode.web.dto.DateMovieDTO;
import fa.appcode.web.entities.DateMovie;
import fa.appcode.web.repository.ShowDateMovieRepository;
import fa.appcode.web.service.ShowDateMovieService;
import fa.appcode.web.service.ShowDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class ShowDateMovieServiceImpl implements ShowDateMovieService {
    @Autowired private
    ShowDateMovieRepository showDateMovieRepository;
    @Autowired private DateMovieConverter dateMovieConverter;
    @Override
    public Map<String, Object> pagingDateMovie(int pageNumber, int pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        Page<DateMovie> dateMovies = showDateMovieRepository.searchMovieByDateName(pageable,search);
        List<DateMovieDTO> dateMoviesList = dateMovies.toList().stream().map(dateMovie ->dateMovieConverter.convertToDTO(dateMovie)).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("dateMovies",dateMoviesList);
        map.put("totalPages",dateMovies.getTotalPages());
        map.put("totalElements",dateMovies.getTotalElements());
        map.put("currentPage",pageNumber);
        return map ;
    }
}
