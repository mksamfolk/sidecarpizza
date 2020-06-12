package com.sidecar.pizza.service;

import com.sidecar.pizza.exception.OrderNotFoundException;
import com.sidecar.pizza.model.OrderStatus;
import com.sidecar.pizza.entity.PizzaOrder;
import com.sidecar.pizza.repository.PizzaOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class PizzaOrderService {

    private PizzaOrderRepository pizzaOrderRepository;

    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    @Transactional
    public void placeOrder(PizzaOrder pizzaOrder, String requestedBy) {
        pizzaOrder.setOrderStatus(OrderStatus.PLACED);
        pizzaOrder.setRequestedBy(requestedBy);
        pizzaOrderRepository.save(pizzaOrder);
    }

    @Transactional
    public void updateOrder(PizzaOrder pizzaOrder, String requestedBy) throws OrderNotFoundException {
        pizzaOrderRepository.findById(pizzaOrder.getId())
                .map(order -> {
                    order.setPizzaType(pizzaOrder.getPizzaType());
                    order.setQuantity(pizzaOrder.getQuantity());
                    order.setOrderStatus(pizzaOrder.getOrderStatus());
                    order.setRequestedBy(requestedBy);
                    return pizzaOrderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException());
    }

    @Transactional
    public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
        return pizzaOrderRepository.findByOrderStatus(orderStatus);
    }

}
