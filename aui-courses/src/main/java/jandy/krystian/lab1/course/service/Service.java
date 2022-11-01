package jandy.krystian.lab1.course.service;

import java.util.List;
import java.util.Optional;

public interface Service<E, K> {

    Optional<E> find(K id);


    List<E> findAll();

    void delete(K id);

    E create(E entity);
}
