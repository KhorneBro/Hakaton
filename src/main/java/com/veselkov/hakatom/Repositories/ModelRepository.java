package com.veselkov.hakatom.Repositories;

import com.veselkov.hakatom.Entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
