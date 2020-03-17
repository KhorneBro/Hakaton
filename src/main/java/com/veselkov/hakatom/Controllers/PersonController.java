package com.veselkov.hakatom.Controllers;

import com.veselkov.hakatom.DTO.PersonDTO;
import com.veselkov.hakatom.DTO.StatisticDTO;
import com.veselkov.hakatom.Services.CarService;
import com.veselkov.hakatom.Services.PersonService;
import com.veselkov.hakatom.Services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity personDTO(@Valid @RequestBody PersonDTO personDTO) {

        if (personDTO.personValidation()) {
            return ResponseEntity.badRequest().build();
        }

        if (personService.validationPersonId(personDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        GregorianCalendar thisCalendar = new GregorianCalendar();
        GregorianCalendar birthdatePerson = new GregorianCalendar();
        birthdatePerson.setTime(personDTO.getBirthdate());

        if (thisCalendar.getTimeInMillis() < birthdatePerson.getTimeInMillis()) {
            return ResponseEntity.badRequest().build();
        }

        personService.personSave(personDTO.getId(),
                personDTO.getName(),
                personDTO.getBirthdate());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
    public @ResponseBody
    Object json(@Valid @RequestParam(value = "personid") Long personId) throws ParseException {
        List list = carService.carByPersonId(personId);

        PersonDTO personDTO = personService.returnPerson(personId);

        if (personDTO == null) {
            return ResponseEntity.notFound().build();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String date1 = simpleDateFormat1.format(personDTO.getBirthdate());
        Date date = simpleDateFormat.parse(date1);


        PersonDTO person = new PersonDTO();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setBirthdate(date);
        person.setCars(list);

        return person;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public @ResponseBody
    Object json() {
        StatisticDTO statisticDTO = personService.returnStatistics();
        return statisticDTO;
    }

    @RequestMapping(value = "/clear")
    public ResponseEntity clearAll() {
        personService.clearAll();
        return ResponseEntity.ok().build();
    }
}
