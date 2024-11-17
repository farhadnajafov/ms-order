package az.company.orders.controller;

import az.company.orders.exception.ServiceUnavailableException;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;
import az.company.orders.service.abstraction.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void  createOrder(@RequestBody @Valid  CreateOrderRequest createOrderRequest){
         orderService.createOrder(createOrderRequest);
    }

    @GetMapping("{id}")
    @CircuitBreaker(name = "getOrderById", fallbackMethod = "fallback")
    public OrderResponse getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    public OrderResponse fallback(Long response, Exception exception){
        throw new ServiceUnavailableException("Service is currently unavailable.");
    }
}
