import dto.OrderItemsDto;
import entity.OrderItems;
import entity.Orders;
import mapper.OrderItemsMapper;
import mapper.OrdersMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.OrderItemsRepository;
import repository.OrdersRepository;
import service.OrderItemsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderItemsServiceTest {
    private OrderItemsRepository orderItemsRepository;
    private OrderItemsService orderItemsService;

    @BeforeEach
    public void setUp() {
        orderItemsRepository = mock(OrderItemsRepository.class);
        orderItemsService = new OrderItemsService(orderItemsRepository);
    }

    @Test
    public void testFindById() {
        Integer orderItemId = 1;
        OrderItems orderItems = new OrderItems();
        Orders orders = new Orders();

        orderItems.setOrderItemID(orderItemId);
        orderItems.setProductName("productName");
        orderItems.setQuantity(1);
        orderItems.setPrice(1.0);
        orders.setOrderID(1);
        orderItems.setOrderID(orders.getOrderID());
        when(orderItemsRepository.findById(orderItemId)).thenReturn(orderItems);

        OrderItemsDto orderItemsDto = orderItemsService.findById(orderItemId);
        assertEquals(orderItems.getOrderItemID(), orderItemsDto.getOrderItemID());
        verify(orderItemsRepository, times(1)).findById(orderItemId);
    }

    @Test
    public void testFindAll() {
        List<OrderItems> orderItemsList = new ArrayList<>();
        OrderItems orderItems = new OrderItems();
        Integer orderItemId = 1;
        Orders orders = new Orders();

        orderItems.setOrderItemID(orderItemId);
        orders.setOrderID(1);
        orderItems.setOrderID(orders.getOrderID());
        orderItemsList.add(orderItems);
        when(orderItemsRepository.findAll()).thenReturn(orderItemsList);

        List<OrderItemsDto> orderItemsListDto = orderItemsService.findAll();
        assertEquals(orderItemsList.size(), orderItemsListDto.size());
        verify(orderItemsRepository, times(1)).findAll();
    }
    @Test
    public void testSave() {
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        Integer orderItemId = 1;
        orderItemsDto.setOrderItemID(orderItemId);
        orderItemsService.save(orderItemsDto);
        verify(orderItemsRepository, times(1)).save(any(OrderItems.class));
    }
    @Test
    public void testUpdate() {
        Integer orderItemId = 1;
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemID(orderItemId);
        when(orderItemsRepository.findById(orderItemId)).thenReturn(orderItems);
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setOrderItemID(orderItemId);
        orderItemsService.update(orderItemsDto);
        verify(orderItemsRepository, times(1)).update(any(OrderItems.class));
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testDeleteById(Integer id) {
        orderItemsService.deleteById(id);

        verify(orderItemsRepository, times(1)).deleteById(id);
    }
}

