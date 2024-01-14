package ru.kuznetsov.homework1.services.abstracts;

import ru.kuznetsov.homework1.exceptions.ResourceNotFoundException;
import ru.kuznetsov.homework1.models.BaseEntity;
import ru.kuznetsov.homework1.repositories.AbstractRepository;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractService<E extends BaseEntity,
        R extends AbstractRepository<E>>
        implements CommonService<E> {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<E> saveAll(Collection<E> batch) {
        return repository.saveAll(batch);
    }

    @Override
    public E getById(Long id) {
        Optional<E> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException(id);
        } else return byId.get();
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<E> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!existById(id)) {
            throw new ResourceNotFoundException(id);
        } else repository.deleteById(id);
    }
}
