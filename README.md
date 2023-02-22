# SQS Order Validator

Project used to demonstrate how to integrate a spring boot application with AWS SQS.

# Requirements

- Java 11
- SpringBoot
- H2 Database
- Maven
- AWS account for using SQS service

# Inserting AWS credentials configuration

* Go to application.yml e replace the amazon.accessKey and amazon.secretKey with your account credentials

# Running the project

Two ways to run the project
```bash
$ mvn spring-boot:run
```
```bash
$ mvn clean package && java -jar target/order-validator-1.0.0-SNAPSHOT.jar
```

# Payload for test

* POST request in http://localhost:8080/api/v1/orders

```json lines
{
    "clientName": "João da Silva",
    "clientScore": 110,
    "cardOwner": "João da Silva",
    "cardNumber": "XXXX-XXXX-XXXX-XXXX",
    "price": 1000,
    "amount": 70
}
```

The order will be recorded and a JMS message sent to the "order-validator" queue. When consuming the message, the validations will be checked and the order status updated.