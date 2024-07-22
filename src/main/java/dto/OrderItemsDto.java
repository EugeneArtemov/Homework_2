package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemsDto {
    private Integer orderItemID;
    private Integer orderID;
    private String productName;
    private Integer quantity;
    private Double price;
}
