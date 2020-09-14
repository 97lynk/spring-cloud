package io.a97lynk.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

}
