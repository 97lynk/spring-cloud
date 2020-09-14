package io.a97lynk.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BaseService<E, D> {

    D toDto(E e);

    E toEntity(D d);

    Page<D> searchAll(Pageable pageable);

    List<D> searchAll();

    void validateToAdd(D d) throws RuntimeException;

    D add(D d);

    void validateToUpdate(D d) throws RuntimeException;

    D updateById(Long id, D d);

    void validateToDelete(Long id) throws RuntimeException;

    void deleteById(Long id);

}
