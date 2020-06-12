package com.sidecar.pizza.dto;

import com.sidecar.pizza.model.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePizzaOrderDto {

    @NotNull
    private String pizzaType;

    @NotNull
    private int quantity;

    @NotNull
    private OrderStatus orderStatus;

}
