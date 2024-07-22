package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderItems {
    private Integer orderItemID;
    private Integer orderID;
    private String productName;
    private Integer quantity;
    private Double price;
}
