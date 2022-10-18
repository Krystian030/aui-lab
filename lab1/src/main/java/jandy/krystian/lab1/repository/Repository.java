package jandy.krystian.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <E, K> {

    Optional<E> find(K id);

    List<E> findAll();

    void delete(K id);

    void create(E entity);

    void update(E entity);
}
