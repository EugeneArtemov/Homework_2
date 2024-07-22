package mapper;

import dto.OrderItemsDto;
import entity.OrderItems;

public class OrderItemsMapper {
    public static OrderItemsDto toDto(OrderItems orderItems) {
        OrderItemsDto orderItemsdto = new OrderItemsDto();
        orderItemsdto.setOrderItemID(orderItems.getOrderItemID());
        orderItemsdto.setOrderID(orderItems.getOrderID());
        orderItemsdto.setProductName(orderItems.getProductName());
        orderItemsdto.setQuantity(orderItems.getQuantity());
        orderItemsdto.setPrice(orderItems.getPrice());
        return orderItemsdto;
    }

    public static OrderItems toEntity(OrderItemsDto orderItemsdto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemID(orderItemsdto.getOrderItemID());
        orderItems.setOrderID(orderItemsdto.getOrderID());
        orderItems.setProductName(orderItemsdto.getProductName());
        orderItems.setQuantity(orderItemsdto.getQuantity());
        orderItems.setPrice(orderItemsdto.getPrice());
        return orderItems;
    }
}
