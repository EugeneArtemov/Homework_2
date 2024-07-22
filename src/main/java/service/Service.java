package service;

import java.util.List;

public interface Service<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T dto);
    void update(T dto);
    void deleteById(Integer id);

}
