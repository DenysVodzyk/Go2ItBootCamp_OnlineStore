package repository;

import entity.Customer;
import entity.Item;
import entity.Order;
import utils.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private CustomerRepository customerRepository;
    private ItemRepository itemRepository;

    public OrderRepository() {
        init();
    }

    private void init() {
        this.customerRepository = new CustomerRepository();
        this.itemRepository = new ItemRepository();
    }

    public void add(Order order) {
        String sql = "INSERT INTO orders(id, customerId, itemId, orderDate) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            for (Item item : order.getItems()) {
                int orderId = order.getId();
                int customerId = customerRepository.getId(order.getCustomer());
                int itemId = item.getId();
                java.sql.Date orderDate = java.sql.Date.valueOf(order.getOrderDate());
                stm.setInt(1, orderId);
                stm.setInt(2, customerId);
                stm.setInt(3, itemId);
                stm.setDate(4, orderDate);

                if (!isInDb(orderId, customerId, itemId, orderDate)) {
                    stm.executeUpdate();
                }
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isInDb(int orderId, int customerId, int itemId, Date orderDate) {
        boolean result = false;
        String sql = "SELECT * FROM orders WHERE id=? AND customerId=? AND itemId=? AND orderDate=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            stm.setInt(2, customerId);
            stm.setInt(3, itemId);
            stm.setDate(4, orderDate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result = true;
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Order getById(int id) {
        Customer customer = null;
        LocalDate orderDate = null;
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                customer = customerRepository.getById(customerId);
                int itemId = rs.getInt("itemId");
                items.add(itemRepository.getById(itemId));
                orderDate = rs.getDate("orderDate").toLocalDate();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return new Order(orderDate, customer, items);
    }

    public List<Order> getByItemId(int itemId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE itemId=" + itemId;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                List<Item> items = new ArrayList<>();
                int customerId = rs.getInt("customerId");
                Customer customer = customerRepository.getById(customerId);
                int item = rs.getInt("itemId");
                items.add(itemRepository.getById(item));
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                orders.add(new Order(orderDate, customer, items));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public Order getByCustomerId(int id) {
        Customer customer = null;
        LocalDate orderDate = null;
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customerId=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                customer = customerRepository.getById(customerId);
                int itemId = rs.getInt("itemId");
                items.add(itemRepository.getById(itemId));
                orderDate = rs.getDate("orderDate").toLocalDate();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return new Order(orderDate, customer, items);
    }


    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                List<Item> items = new ArrayList<>();
                int customerId = rs.getInt("customerId");
                Customer customer = customerRepository.getById(customerId);
                int itemId = rs.getInt("itemId");
                items.add(itemRepository.getById(itemId));
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                orders.add(new Order(orderDate, customer, items));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

}

