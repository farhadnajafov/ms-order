package az.company.orders.mapper;

import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.model.client.response.PaymentResponse;
import az.company.orders.model.client.response.ProductResponse;
import az.company.orders.model.enums.OrderStatus;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;

import java.time.LocalDateTime;

import static az.company.orders.model.enums.OrderStatus.PENDING;

public enum OrderMapper {

    ORDER_MAPPER;

    public OrderEntity buildOrderMapper(CreateOrderRequest createOrderRequest){
        return OrderEntity.builder()
                .productId(createOrderRequest.getProductId())
                .quantity(createOrderRequest.getQuantity())
                .status(PENDING)
                .createdAt(LocalDateTime.now())
                .build();

    }

    public OrderResponse buildOrderResponse(OrderEntity orderEntity,
                                            ProductResponse productResponse,
                                            PaymentResponse paymentResponse){
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .productId(orderEntity.getProductId())
                .quantity(orderEntity.getQuantity())
                .amount(orderEntity.getAmount())
                .status(orderEntity.getStatus())
                .createdAt(orderEntity.getCreatedAt())
                .product(productResponse)
                .payment(paymentResponse)
                .build();
    }
}
