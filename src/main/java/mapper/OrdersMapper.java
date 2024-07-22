package mapper;

import dto.OrderItemsDto;
import dto.OrdersDto;
import entity.OrderItems;
import entity.Orders;

public class OrdersMapper {
    public static OrdersDto toDto(Orders orders){
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderID(orders.getOrderID());
        ordersDto.setOrderDate(orders.getOrderDate());
        ordersDto.setCustomerName(orders.getCustomerName());
        ordersDto.setTotalAmount(orders.getTotalAmount());
        ordersDto.setOrderItems(orders.getOrderItems().stream().map(OrderItemsMapper::toDto).toList());
        return ordersDto;
    }
    public static Orders toEntity(OrdersDto ordersDto){
        Orders orders = new Orders();
        orders.setOrderID(ordersDto.getOrderID());
        orders.setOrderDate(ordersDto.getOrderDate());
        orders.setCustomerName(ordersDto.getCustomerName());
        orders.setTotalAmount(ordersDto.getTotalAmount());
        orders.setOrderItems(ordersDto.getOrderItems().stream().map(OrderItemsMapper::toEntity).toList());
        return orders;
    }
}
