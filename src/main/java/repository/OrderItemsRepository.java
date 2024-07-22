package repository;

import config.DBConnectionManager;
import entity.OrderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsRepository implements Repository<OrderItems> {
    @Override
    public OrderItems findById(Integer id) {
        OrderItems orderItems = new OrderItems();
        String query = "SELECT * FROM orderitems WHERE OrderItemID = ?";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer order_item_id = resultSet.getInt("OrderItemID");
                Integer order_id = resultSet.getInt("OrderID");
                String ProductName = resultSet.getString("ProductName");
                Integer quantity = resultSet.getInt("Quantity");
                Double price = resultSet.getDouble("Price");

                orderItems.setOrderItemID(order_item_id);
                orderItems.setOrderID(order_id);
                orderItems.setProductName(ProductName);
                orderItems.setQuantity(quantity);
                orderItems.setPrice(price);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public List<OrderItems> findAll() {
        String query = "SELECT * FROM orderitems";
        List<OrderItems> list = new ArrayList<>();
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Integer order_item_id = resultSet.getInt("OrderItemID");
                Integer order_id = resultSet.getInt("OrderID");
                String ProductName = resultSet.getString("ProductName");
                Integer quantity = resultSet.getInt("Quantity");
                Double price = resultSet.getDouble("Price");

                OrderItems orderItems = new OrderItems();
                orderItems.setOrderItemID(order_item_id);
                orderItems.setOrderID(order_id);
                orderItems.setProductName(ProductName);
                orderItems.setQuantity(quantity);
                orderItems.setPrice(price);
                list.add(orderItems);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void save(OrderItems entity) {
        String query = "INSERT INTO orderitems (OrderID, ProductName, Quantity, Price) VALUES (?, ?, ?, ?)";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, entity.getOrderID());
            preparedStatement.setString(2, entity.getProductName());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setDouble(4, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OrderItems entity) {
        String query = "UPDATE orderitems set OrderID = ?, ProductName = ?, Quantity = ?, Price = ? WHERE OrderItemID = ?";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getOrderID());
            preparedStatement.setString(2, entity.getProductName());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setDouble(4, entity.getPrice());
            preparedStatement.setInt(5, entity.getOrderItemID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM orderitems WHERE OrderItemID = ?";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
