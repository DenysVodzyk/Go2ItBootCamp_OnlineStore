package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private List<Order> order = new ArrayList<>();
    private LocalDate dateOfPurchase;

    public Customer(String name, LocalDate dateOfBirth, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(String name, LocalDate dateOfBirth, String address, Gender gender, List<Item> items, LocalDate dateOfLastPurchase) {
        this(name, dateOfBirth, address);
        this.gender = gender;
        order.add(new Order(dateOfLastPurchase, Customer.this, items));
    }

    public Customer(String name, LocalDate dateOfBirth, String address, Gender gender, String phoneNumber, List<Item> items, LocalDate dateOfLastPurchase) {
        this(name, dateOfBirth, address, gender, items, dateOfLastPurchase);
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Order> getOrder() {
        return order;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", order=" + order +
                '}';
    }
}
