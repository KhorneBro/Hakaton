package com.veselkov.hakatom.Repositories;

import com.veselkov.hakatom.Entities.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Cars, Long> {
    List<Cars> findByOwnerId(@Param("id") Long id);
}
