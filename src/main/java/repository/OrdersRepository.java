package repository;

import config.DBConnectionManager;
import entity.Orders;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository implements Repository<Orders>{

    @Override
    public Orders findById(Integer id) {
        Orders orders = new Orders();
        String query = "SELECT * FROM orders WHERE OrderID = ?";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt("OrderID");
                LocalDate orderDate = resultSet.getDate("OrderDate").toLocalDate();
                String customerName = resultSet.getString("CustomerName");
                Double orderAmount = resultSet.getDouble("TotalAmount");

                orders.setOrderID(orderId);
                orders.setOrderDate(orderDate);
                orders.setCustomerName(customerName);
                orders.setTotalAmount(orderAmount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            Orders order = new Orders();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt("OrderID");
                LocalDate orderDate = resultSet.getDate("OrderDate").toLocalDate();
                String customerName = resultSet.getString("CustomerName");
                Double orderAmount = resultSet.getDouble("TotalAmount");

                order.setOrderID(orderId);
                order.setOrderDate(orderDate);
                order.setCustomerName(customerName);
                order.setTotalAmount(orderAmount);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public void save(Orders entity) {
        String query = "INSERT INTO orders (OrderDate, CustomerName, TotalAmount) VALUES (?, ?, ?)";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            LocalDate orderDate = entity.getOrderDate();
            String customerName = entity.getCustomerName();
            Double totalAmount = entity.getTotalAmount();
            preparedStatement.setDate(1, Date.valueOf(orderDate));
            preparedStatement.setString(2, customerName);
            preparedStatement.setDouble(3, totalAmount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Orders entity) {
        String query = "UPDATE orders SET OrderDate = ?, CustomerName = ?, TotalAmount = ? WHERE OrderID = ?";
        DBConnectionManager.init();
        try (Connection connection = DBConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Integer orderID = entity.getOrderID();
            LocalDate orderDate = entity.getOrderDate();
            String customerName = entity.getCustomerName();
            Double totalAmount = entity.getTotalAmount();
            preparedStatement.setDate(1, Date.valueOf(orderDate));
            preparedStatement.setString(2, customerName);
            preparedStatement.setDouble(3, totalAmount);
            preparedStatement.setInt(4, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM orders WHERE OrderID = ?";
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
