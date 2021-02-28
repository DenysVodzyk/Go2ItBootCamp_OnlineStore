package repository;

import entity.Customer;
import entity.Item;
import entity.Order;
import utils.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private CustomerRepository customerRepository;

    public OrderRepository() {
        init();
    }

    private void init() {
        this.customerRepository = new CustomerRepository();
    }

    public void add(Order order) {
        String sql = "INSERT INTO orders(id, customerId, itemId, orderDate) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, order.getId());
            stm.setInt(2, customerRepository.getId(order.getCustomer()));
            for (Item item : order.getItems()) {
                stm.setInt(3, item.getId());
            }
            java.sql.Date orderDate = java.sql.Date.valueOf(order.getOrderDate());
            stm.setDate(4, orderDate);
            stm.executeUpdate();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
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
                items.add(new Item(itemId));
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
                items.add(new Item(itemId));
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                orders.add(new Order(orderDate, customer, items));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }


//    public List<Item> getAll() {
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT * FROM item";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement stm = con.prepareStatement(sql)) {
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int itemId = rs.getInt("id");
//                String title = rs.getString("title");
//                int code = rs.getInt("code");
//                String producer = rs.getString("producer");
//                LocalDateTime lastUpdate = rs.getTimestamp("dateOfLastUpdate").toLocalDateTime();
//                items.add(new Item(itemId, title, code, producer, lastUpdate));
//            }
//        } catch (SQLException | IOException throwables) {
//            throwables.printStackTrace();
//        }
//        return items;
//    }

}

