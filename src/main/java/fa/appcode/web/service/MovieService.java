package fa.appcode.web.service;

import fa.appcode.web.dto.MovieDTO;
import fa.appcode.web.entities.Movie;

import java.util.List;

public interface MovieService extends BaseService<MovieDTO,Integer> {
    public List<MovieDTO> searchAllMovie(String search);
    public List<MovieDTO> findAll();
}
