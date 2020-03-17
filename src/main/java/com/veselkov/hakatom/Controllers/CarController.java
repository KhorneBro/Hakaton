package com.veselkov.hakatom.Controllers;

import com.veselkov.hakatom.DTO.CarsDTO;
import com.veselkov.hakatom.DTO.PersonDTO;
import com.veselkov.hakatom.Services.CarService;
import com.veselkov.hakatom.Services.PersonService;
import com.veselkov.hakatom.Services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.GregorianCalendar;

@Controller
public class CarController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity carDTO(@Valid @RequestBody CarsDTO carsDTO) {

        if (carsDTO.carValidation()) {
            return ResponseEntity.badRequest().build();
        }

        if (carService.validationCarId(carsDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        PersonDTO personDTO = personService.returnPerson(carsDTO.getOwnerId());
        if (personDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        GregorianCalendar thisCalendar = new GregorianCalendar();
        GregorianCalendar birthdatePerson = new GregorianCalendar();
        birthdatePerson.setTime(personDTO.getBirthdate());
        long millisecond = thisCalendar.getTimeInMillis() - birthdatePerson.getTimeInMillis();

        if (millisecond < 567648000000L) {
            return ResponseEntity.badRequest().build();
        }

        carService.carSave(carsDTO.getId(),
                carsDTO.getModel(),
                carsDTO.getHorsepower(),
                carsDTO.getOwnerId());
        return ResponseEntity.ok().build();
    }
}
