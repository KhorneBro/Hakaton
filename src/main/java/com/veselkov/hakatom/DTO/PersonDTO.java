package com.veselkov.hakatom.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

public class PersonDTO {

    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", lenient = OptBoolean.FALSE)
    private Date birthdate;

    private List cars;

    public List getCars() {
        return cars;
    }

    public void setCars(List cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }

    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean personValidation() {

        return (this.getId() == null ||
                this.getId() == 0 ||
                this.getName() == null ||
                this.getBirthdate() == null);
    }
}
