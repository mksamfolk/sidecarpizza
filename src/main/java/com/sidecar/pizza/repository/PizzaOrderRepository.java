package com.sidecar.pizza.repository;

import com.sidecar.pizza.entity.PizzaOrder;
import com.sidecar.pizza.model.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, UUID> {

    List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus);

}
