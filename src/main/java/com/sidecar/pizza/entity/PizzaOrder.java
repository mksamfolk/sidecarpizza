package com.sidecar.pizza.entity;

import com.sidecar.pizza.model.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "pizza_order", schema = "sidecar")
public class PizzaOrder {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String pizzaType;
    private int quantity;
    private OrderStatus orderStatus;
    private String requestedBy;

}
