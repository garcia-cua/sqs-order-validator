package com.fernando.sqsordervalidator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -353635619496274756L;

    @GeneratedValue
    @Id
    private Long id;

    @NotNull(message = "Client name cannot be null")
    @Column(name = "client_name")
    private String clientName;

    @NotNull(message = "Client score cannot be null")
    @Column(name = "client_score")
    private Integer clientScore;

    @NotNull(message = "Card owner cannot be null")
    @Column(name = "card_owner")
    private String cardOwner;

    @NotNull(message = "Card number cannot be null")
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull(message = "Price cannot be null")
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount")
    private Integer amount;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private String status;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientScore() {
        return clientScore;
    }

    public void setClientScore(Integer clientScore) {
        this.clientScore = clientScore;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(OrderBuilder orderBuilder) {
        this.id = orderBuilder.id;
        this.clientName = orderBuilder.clientName;
        this.clientScore = orderBuilder.clientScore;
        this.cardOwner = orderBuilder.cardOwner;
        this.price = orderBuilder.price;
        this.amount = orderBuilder.amount;
        this.status = orderBuilder.status;
    }

    public static class OrderBuilder {
        private Long id;
        private String clientName;
        private Integer clientScore;
        private String cardOwner;
        private String cardNumber;
        private BigDecimal price;
        private Integer amount;
        private String status;

        public OrderBuilder() {
            from(new Order());
        }

        public OrderBuilder from(Order order) {
            return this.id(order.getId())
                    .clientName(order.getClientName())
                    .clientScore(order.getClientScore())
                    .cardOwner(order.getCardOwner())
                    .cardNumber(order.getCardNumber())
                    .price(order.getPrice())
                    .amount(order.getAmount())
                    .status(order.getStatus());
        }

        private OrderBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public OrderBuilder clientScore(Integer clientScore) {
            this.clientScore = clientScore;
            return this;
        }

        public OrderBuilder cardOwner(String cardOwner) {
            this.cardOwner = cardOwner;
            return this;
        }

        public OrderBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public OrderBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderBuilder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}

