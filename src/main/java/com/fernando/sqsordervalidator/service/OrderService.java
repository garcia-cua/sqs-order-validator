package com.fernando.sqsordervalidator.service;

import com.fernando.sqsordervalidator.model.Order;
import com.fernando.sqsordervalidator.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);
    private static final Integer SIX_HUNDRED = 600;
    private static final Integer SIXTY = 60;
    private static final String ORDER_DESTINATION = "order-validator";
    private static final String ORDER_VALID = "Order valid";
    private static final String ORDER_NOT_VALID = "Order is not valid";
    private final OrderRepository orderRepository;
    private final JmsTemplate jmsTemplate;
    private enum OrderStatus { PROCESSED, RECEIVED, WITH_PROBLEM }

    public OrderService(OrderRepository orderRepository, JmsTemplate jmsTemplate) {
        this.orderRepository = orderRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order create(Order order) {
        order.setStatus(OrderStatus.RECEIVED.toString());
        Order orderSaved = orderRepository.save(order);
        sendMessageToOrderValidation(orderSaved);
        return orderSaved;
    }

    private void sendMessageToOrderValidation (Order order) throws JmsException {
        jmsTemplate.convertAndSend(ORDER_DESTINATION, order.getId().toString());
    }

    private boolean isOrderValid(Order order) {
        return order.getClientName().equals(order.getCardOwner()) &&
                order.getAmount() <= SIX_HUNDRED &&
                order.getClientScore() >= SIXTY;
    }

    @JmsListener(destination = ORDER_DESTINATION)
    private void orderValidator(String id) {
        Optional<Order> orderOptional = orderRepository.findById(Long.parseLong(id));

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();

            if (isOrderValid(order)) {
                order.setStatus(OrderStatus.PROCESSED.toString());
                logger.info(ORDER_VALID);
            } else {
                order.setStatus(OrderStatus.WITH_PROBLEM.toString());
                logger.info(ORDER_NOT_VALID);
            }

            orderRepository.save(order);
        }
    }

}
