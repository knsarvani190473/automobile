package com.example.Automobile.DTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class CarDTO {
    private int id;
    private String brandName;
    private String model;
    private int cyear;
    private Set<ColorsDTO> colorsDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCyear() {
        return cyear;
    }

    public void setCyear(int cyear) {
        this.cyear = cyear;
    }

    public Set<ColorsDTO> getColorsDTOS() {
        return colorsDTO;
    }

    public void setColorsDTOS(Set<ColorsDTO> colorsDTOS) {
        this.colorsDTO = colorsDTOS;
    }
}
