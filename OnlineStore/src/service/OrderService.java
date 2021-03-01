package service;

import entity.Customer;
import entity.Order;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        init();
    }

    private void init() {
        this.orderRepository = new OrderRepository();
    }

    public List<Order> parseAll(String customersFilePath) {
        CustomerService customerService = new CustomerService();
        List<Order> orders = new ArrayList<>();
        List<Customer> customers = customerService.parseAll(customersFilePath);

        for (int i = 0; i < customers.size(); i++) {
            Order order = customers.get(i).getOrder();
            order.setId(i + 1);
            orders.add(order);
        }
        return orders;
    }

    public void addAllToDB(List<Order> orders) {
        for (Order order : orders) {
            orderRepository.add(order);
        }
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Order getById(int id) {
        return orderRepository.getById(id);
    }

    public List<Order> getByItemId(int itemId) {
        return orderRepository.getByItemId(itemId);
    }

    public Order getByCustomerId(int customerId) {
        return orderRepository.getByCustomerId(customerId);
    }


}
