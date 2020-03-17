package com.veselkov.hakatom.Repositories;

import com.veselkov.hakatom.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
