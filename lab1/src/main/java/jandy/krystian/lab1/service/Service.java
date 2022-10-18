package jandy.krystian.lab1.service;

import java.util.List;
import java.util.Optional;

public interface Service<E, K> {

    Optional<E> find(K id);

    Optional<E> find();

    List<E> findAll();

    void delete(K id);

    void delete();

    void create(E entity);

    void create();

    void update(E entity);

    void update();
}
