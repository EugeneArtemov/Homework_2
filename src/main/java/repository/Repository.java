package repository;

import java.util.List;

public interface Repository<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void deleteById(Integer id);
}
