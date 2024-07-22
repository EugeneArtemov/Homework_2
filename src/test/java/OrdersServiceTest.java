import dto.OrdersDto;
import entity.Orders;
import mapper.OrdersMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import repository.OrdersRepository;
import service.OrdersService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class OrdersServiceTest {
    private OrdersRepository ordersRepository;
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        ordersRepository = Mockito.mock(OrdersRepository.class);
        ordersService = new OrdersService(ordersRepository);
    }

    @Test
    void testFindById(){
        Integer orderId = 1;
        Orders order = new Orders();
        order.setOrderID(orderId);

        when(ordersRepository.findById(orderId)).thenReturn(order);

        OrdersDto ordersDto = ordersService.findById(orderId);
        assertEquals(order.getOrderID(), ordersDto.getOrderID());
        verify(ordersRepository, times(1)).findById(orderId);
    }

    @Test
    public void testFindAll() {
        List<Orders> ordersList = new ArrayList<>();
        Orders order = new Orders();
        Integer orderId = 1;

        order.setOrderID(orderId);
        ordersList.add(order);
        when(ordersRepository.findAll()).thenReturn(ordersList);

        List<OrdersDto> ordersListDto = ordersService.findAll();
        assertEquals(ordersList.size(), ordersListDto.size());
        verify(ordersRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        OrdersDto ordersDto = new OrdersDto();
        Integer orderId = 1;
        ordersDto.setOrderID(orderId);
        ordersService.save(ordersDto);
        verify(ordersRepository, times(1)).save(any(Orders.class));
    }

    @Test
    public void testUpdate() {
        Integer orderId = 1;
        Orders order = new Orders();
        order.setOrderID(orderId);


        when(ordersRepository.findById(orderId)).thenReturn(order);

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderID(orderId);
        ordersService.update(ordersDto);
        verify(ordersRepository, times(1)).save(any(Orders.class));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testDeleteById(Integer id) {
        ordersService.deleteById(id);

        verify(ordersRepository, times(1)).deleteById(id);
    }
}
