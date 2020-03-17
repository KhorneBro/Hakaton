package com.veselkov.hakatom.Services;

import com.veselkov.hakatom.DTO.CarsDTO;
import com.veselkov.hakatom.DTO.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class ValidationService {

    public boolean carValidation(CarsDTO carsDTO) {
        return (carsDTO.getId() == null ||
                carsDTO.getHorsepower() == null ||
                carsDTO.getHorsepower() <= 0 ||
                carsDTO.getModel().equals("") ||
                carsDTO.getModel().charAt(0) == '-' ||
                carsDTO.getModel() == null ||
                carsDTO.getOwnerId() == 0 ||
                carsDTO.getOwnerId() == null);
    }

    public boolean personValidation(PersonDTO personDTO) {

        GregorianCalendar thisCalendar = new GregorianCalendar();
        GregorianCalendar birthdatePerson = new GregorianCalendar();
        birthdatePerson.setTime(personDTO.getBirthdate());

        return (personDTO.getId() == null ||
                personDTO.getName() == null ||
                personDTO.getBirthdate() == null ||
                thisCalendar.getTimeInMillis() < birthdatePerson.getTimeInMillis() ||
                (thisCalendar.getTimeInMillis() - birthdatePerson.getTimeInMillis()) < 567648000000L);
    }
}
