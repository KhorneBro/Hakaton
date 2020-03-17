package com.veselkov.hakatom.Entities;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Cars implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "horsepower")
    @Min(1)
    @NotNull
    private Integer horsepower;

    @Basic
    @Column(name = "ownerId")
    @NotNull
    private Long ownerId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    private Model models;

    public Cars() {
    }

    public Cars(Long id, @NotNull Model model, @NotNull @Min(1) Integer horsepower, @NotNull Long ownerId, Person person) {
        this.id = id;
        this.models = model;
        this.horsepower = horsepower;
        this.ownerId = ownerId;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Model getModels() {
        return models;
    }

    public void setModels(Model models) {
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
