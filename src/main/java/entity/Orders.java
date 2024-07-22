package entity;

import dto.OrdersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Orders {
    private Integer orderID;
    private LocalDate orderDate;
    private String customerName;
    private Double TotalAmount;
    private List<OrderItems> orderItems = new ArrayList<>();
}
