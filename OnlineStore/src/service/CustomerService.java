package service;

import entity.Customer;
import entity.Gender;
import entity.Order;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private ItemService itemService;
    private OrderService orderService;
    private CustomerRepository customerRepository;

    public CustomerService() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
        this.customerRepository = new CustomerRepository();
        this.orderService = new OrderService();
    }

    public void save(List<Customer> customers) {
        for (Customer customer : customers) {
            customerRepository.add(customer);
        }
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        for (Customer customer : customerRepository.getAll()) {
            setCustomerOrder(customer);
            customers.add(customer);
        }
        return customers;
    }

    public Customer getById(int id) {
        Customer customer = customerRepository.getById(id);
        setCustomerOrder(customer);
        return customer;
    }

    public Customer getByName(String name) {
        Customer customer = customerRepository.getByName(name);
        setCustomerOrder(customer);
        return customer;
    }

    public void setCustomerOrder(Customer customer) {
        Order order = getCustomerOrder(customer);
        customer.setOrder(order);
    }

    public Order getCustomerOrder(Customer customer) {
        return orderService.getByCustomerId(getId(customer));
    }

    public int getId(Customer customer) {
        return customerRepository.getId(customer);
    }

    public List<Customer> getCustomersByGender(List<Customer> customers, Gender gender) {
        List<Customer> sortedCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getGender() == gender)
                sortedCustomers.add(customer);
        }
        return sortedCustomers;
    }
}
