package com.veselkov.hakatom.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private long id;

    @Basic
    @Column(name = "name")
    @NotNull
    private String name;

    @Basic
    @Column(name = "birthdate")
    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthdate;

    public Person(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cars> cars;

    public Person(@NotNull Long id, @NotNull String name, @Past Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Person() {

    }

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
