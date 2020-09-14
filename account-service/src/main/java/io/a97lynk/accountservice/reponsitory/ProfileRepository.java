package io.a97lynk.accountservice.reponsitory;

import io.a97lynk.accountservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileReponsitory extends JpaRepository<Profile, Integer> {
}
