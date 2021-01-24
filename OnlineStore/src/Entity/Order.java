package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate oderDate;
    private Customer customer;
    private List<Item> items = new ArrayList<>();

    public Order(LocalDate oderDate, Customer customer, List<Item> items) {
        this.oderDate = oderDate;
        this.customer = customer;
        this.items = items;
    }

    public LocalDate getOderDate() {
        return oderDate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setOderDate(LocalDate oderDate) {
        this.oderDate = oderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oderDate=" + oderDate +
                ", customer=" + customer.getName() +
                ", items=" + items +
                '}';
    }
}
