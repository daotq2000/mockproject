package fa.appcode.web.service;

import fa.appcode.web.dto.MovieDTO;

import java.text.ParseException;
import java.util.Map;

public interface BaseService<E,K> {
    public Map<String,Object> paging(int page,int size,String search, String field,String order);
    public E save(E e) throws ParseException;
    public E update(E e, K id);
    public boolean delete(K id);
    public E findById(K id) throws ParseException;
}
