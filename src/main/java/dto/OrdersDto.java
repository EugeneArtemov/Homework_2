package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdersDto {
    private Integer orderID;
    private LocalDate orderDate;
    private String customerName;
    private Double TotalAmount;
    private List<OrderItemsDto> orderItems = new ArrayList<>();

}
