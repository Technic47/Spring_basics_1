package ru.kuznetsov.homework1.services.abstracts;

import ru.kuznetsov.homework1.models.BaseEntity;

import java.util.Collection;
import java.util.List;

public interface CommonService<E extends BaseEntity> {
    E save(E entity);

    Iterable<E> saveAll(Collection<E> batch);

    E getById(Long id);

    boolean existById(Long id);

    Iterable<E> getAll();

    void deleteById(Long id);
}
