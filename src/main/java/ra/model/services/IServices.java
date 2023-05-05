package ra.model.services;

import java.util.List;

 public interface IServices <T,E>{
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(E id);
    T findById(E id);
}
