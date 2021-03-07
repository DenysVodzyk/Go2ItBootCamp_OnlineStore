package service;

import entity.Item;
import entity.Order;
import repository.OrderRepository;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        init();
    }

    private void init() {
        this.orderRepository = new OrderRepository();
    }

    public void save(List<Order> orders) {
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

    public List<Item> getAllItemsFromAllOrders() {
        return orderRepository.getAllItemsFromAllOrders();
    }
}
