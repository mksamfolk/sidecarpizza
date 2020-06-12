package com.sidecar.pizza.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlacePizzaOrderDto {

    @NotNull
    private String pizzaType;

    @NotNull
    private int quantity;

}
