package com.veselkov.hakatom.Services;

import com.veselkov.hakatom.DTO.PersonDTO;
import com.veselkov.hakatom.DTO.StatisticDTO;
import com.veselkov.hakatom.Entities.Person;
import com.veselkov.hakatom.Repositories.CarRepository;
import com.veselkov.hakatom.Repositories.PersonRepository;
import com.veselkov.hakatom.Repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CarService carService;

    /**
     * Сохранение
     */
    public void personSave(Long id, String name, Date birthdate) {
        personRepository.save(new Person(id, name, birthdate));
    }

    public boolean validationPersonId(Long id) {
        Optional<Person> notnull = personRepository.findById(id);

        if (notnull.equals(Optional.empty())) {
            return false;
        } else {
            return true;
        }
    }


    public PersonDTO returnPerson(Long id) {
        PersonDTO dto = new PersonDTO();

        Person person;
        person = personRepository.findById(id).orElse(null);

        if (person != null) {
            dto.setId(person.getId());
            dto.setName(person.getName());
            dto.setBirthdate(person.getBirthdate());
            return dto;

        } else {
            return null;
        }
    }

    public StatisticDTO returnStatistics() {
        StatisticDTO statistics = new StatisticDTO();
        statistics.setPersoncount(personRepository.count());
        statistics.setCarcount(carRepository.count());
        statistics.setUniquevendorcount(vendorRepository.countDistinctVendorBy());
        return statistics;
    }

    public void clearAll() {
        carRepository.deleteAll();
        personRepository.deleteAll();
    }
}
