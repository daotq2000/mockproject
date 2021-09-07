package fa.appcode.web.service;

import java.util.Map;

public interface ShowDateMovieService {
    Map<String,Object> pagingDateMovie(int pageNumber, int pageSize, String search);
}
