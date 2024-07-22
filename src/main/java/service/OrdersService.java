package service;

import dto.OrdersDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mapper.OrderItemsMapper;
import mapper.OrdersMapper;
import repository.OrdersRepository;

import java.util.List;
@RequiredArgsConstructor
public class OrdersService implements Service<OrdersDto>{
    private final OrdersRepository repository;


    @Override
    public OrdersDto findById(Integer id) {
        return OrdersMapper.toDto(repository.findById(id));
    }

    @Override
    public List<OrdersDto> findAll() {
        return repository.findAll().stream().map(OrdersMapper::toDto).toList();
    }

    @Override
    public void save(OrdersDto dto) {
        repository.save(OrdersMapper.toEntity(dto));
    }

    @Override
    public void update(OrdersDto dto) {
        repository.save(OrdersMapper.toEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
