package service;

import dto.OrderItemsDto;
import entity.OrderItems;
import lombok.RequiredArgsConstructor;
import mapper.OrderItemsMapper;
import repository.OrderItemsRepository;

import java.util.List;
@RequiredArgsConstructor
public class OrderItemsService implements Service<OrderItemsDto> {
    private final OrderItemsRepository repository;


    @Override
    public OrderItemsDto findById(Integer id) {
        return OrderItemsMapper.toDto(repository.findById(id));
    }

    @Override
    public List<OrderItemsDto> findAll() {
        return repository.findAll().stream().map(OrderItemsMapper::toDto).toList();
    }

    @Override
    public void save(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = OrderItemsMapper.toEntity(orderItemsDto);
        repository.save(orderItems);
    }

    @Override
    public void update(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = OrderItemsMapper.toEntity(orderItemsDto);
        repository.update(orderItems);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
