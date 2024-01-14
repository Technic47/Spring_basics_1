package ru.kuznetsov.homework1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.kuznetsov.homework1.models.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository<T extends BaseEntity> extends CrudRepository<T, Long> {
}
