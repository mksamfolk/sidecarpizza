package com.sidecar.pizza.controller;

import com.sidecar.pizza.dto.PizzaOrderDto;
import com.sidecar.pizza.dto.PlacePizzaOrderDto;
import com.sidecar.pizza.dto.UpdatePizzaOrderDto;
import com.sidecar.pizza.entity.PizzaOrder;
import com.sidecar.pizza.exception.OrderNotFoundException;
import com.sidecar.pizza.model.OrderStatus;
import com.sidecar.pizza.service.PizzaOrderService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@Timed
@RestController
@Slf4j
public class PizzaOrderController {

    private ModelMapper modelMapper;
    private Counter placeOrderCounter;
    private Counter updateOrderCounter;

    private PizzaOrderService pizzaOrderService;

    public PizzaOrderController(
            ModelMapper modelMapper,
            PizzaOrderService pizzaOrderService,
            MeterRegistry meterRegistry) {
        this.modelMapper = modelMapper;
        this.pizzaOrderService = pizzaOrderService;
        this.placeOrderCounter = meterRegistry.counter("pizza-order", "action", "place");
        this.updateOrderCounter = meterRegistry.counter("pizza-order", "action", "update");
    }

    @ApiOperation(value = "${pizza-order.place}")
    @PostMapping("/api/v1/pizza-orders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void placeOrder(Principal principal, @Valid @RequestBody PlacePizzaOrderDto newPizzaOrderRequest) {
        placeOrderCounter.increment();
        pizzaOrderService.placeOrder(fromDto(newPizzaOrderRequest), principal.getName());
    }

    @ApiOperation(value = "${pizza-order.update}")
    @PutMapping("/api/v1/pizza-orders/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderStatus(
            Principal principal, @PathVariable("orderId") UUID orderId,
            @Valid @RequestBody UpdatePizzaOrderDto updatePizzaOrderRequest) throws OrderNotFoundException {
        updateOrderCounter.increment();
        pizzaOrderService.updateOrder(fromDto(orderId, updatePizzaOrderRequest), principal.getName());
    }

    @ApiOperation(value = "${pizza-order.get}")
    @GetMapping("/api/v1/pizza-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<PizzaOrderDto> findByStatus(@RequestParam("orderStatus") OrderStatus orderStatus) {
        return pizzaOrderService.findByOrderStatus(orderStatus).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    private PizzaOrder fromDto(PlacePizzaOrderDto newPizzaOrderRequest) {
        return modelMapper.map(newPizzaOrderRequest, PizzaOrder.class);
    }

    private PizzaOrder fromDto(UUID orderId, UpdatePizzaOrderDto updatePizzaOrderDto) {
        PizzaOrder pizzaOrder = modelMapper.map(updatePizzaOrderDto, PizzaOrder.class);
        pizzaOrder.setId(orderId);
        return pizzaOrder;
    }

    private PizzaOrderDto toDto(PizzaOrder pizzaOrder) {
        return modelMapper.map(pizzaOrder, PizzaOrderDto.class);
    }

}
