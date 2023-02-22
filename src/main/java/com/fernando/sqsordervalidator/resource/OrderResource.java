package com.fernando.sqsordervalidator.resource;

import com.fernando.sqsordervalidator.model.Order;
import com.fernando.sqsordervalidator.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderResource {

    final private OrderService orderService;

    public OrderResource(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Order> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.create(order));
    }

}
