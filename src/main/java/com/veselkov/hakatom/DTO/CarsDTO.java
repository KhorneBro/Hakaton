package com.veselkov.hakatom.DTO;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Min;

public class CarsDTO {

    @NotNull
    private Long id;

    @NotNull
    private String model;

    @NotNull
    @Min(1)
    private Integer horsepower;

    @NotNull
    private Long ownerId;

    public CarsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model != null) {
            this.model = model;
        }
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        if (horsepower != null)
            this.horsepower = horsepower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        if (id != null) {
            this.ownerId = ownerId;
        }
    }

    public boolean carValidation() {
        return (this.getId() == null ||
                this.getHorsepower() == null ||
                this.getHorsepower() <= 0 ||
                this.getModel() == null ||
                this.getModel().equals("") ||
//                this.getModel().charAt(0) == '-' ||
                this.getOwnerId() == null);
    }
}
