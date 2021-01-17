package Service;

import Entity.Customer;
import Entity.Gender;

import java.util.HashSet;
import java.util.Set;

public class CollectionService {
    private Set<Customer> customers = new HashSet<>();

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Set<Customer> getWomen(Set<Customer> customers) {
        Set<Customer> women = new HashSet<>();
        for (Customer customer : customers) {
            if (customer.getGender().equals(Gender.FEMALE)) {
                women.add(customer);
            }
        }
        return women;
    }
}
