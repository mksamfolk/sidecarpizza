package com.sidecar.pizza.dto;

import com.sidecar.pizza.model.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PizzaOrderDto {

    private UUID id;
    private String pizzaType;
    private int quantity;
    private OrderStatus orderStatus;
    private String requestedBy;

}
