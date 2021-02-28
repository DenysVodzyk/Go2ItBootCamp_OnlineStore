package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private LocalDate orderDate;
    private Customer customer;
    private List<Item> items;

    public Order(LocalDate orderDate, Customer customer, List<Item> items) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oderDate=" + orderDate +
                ", customer=" + customer.getName() +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, customer, items);
    }
}
