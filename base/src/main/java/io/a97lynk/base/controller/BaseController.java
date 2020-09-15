package io.a97lynk.base.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseController<D> {

    D create(D d);

    D searchById(Long id);

    List<D> searchAll();

    Page<D> searchAll(Pageable pageable);

    D update(Long id, D d);

    void delete(Long id);

}
