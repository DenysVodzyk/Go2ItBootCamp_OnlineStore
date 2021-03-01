package service;

import entity.Customer;
import entity.Gender;

import java.time.LocalDate;
import java.util.*;

public class CollectionService {
    private Set<Customer> customers = new HashSet<>();
    private Map<List<Integer>, LocalDate> items = new HashMap<>();

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

//    public void addItem(Customer customer) {
//        items.put(customer.getLastPurchases(), customer.getDateOfLastPurchase());
//    }

    public Map<List<Integer>, LocalDate> getItems() {
        return items;
    }

    public List<Integer> getItemsDuringParticularWeekend(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(1);
        List<Integer> itemsDuringWeekend = new ArrayList<>();

        for (Map.Entry<List<Integer>, LocalDate> entry : items.entrySet()) {
            if (entry.getValue().equals(startDate) || entry.getValue().equals(endDate)) {
                for (Integer item : entry.getKey()) {
                    itemsDuringWeekend.add(item);
                }
            }
        }
        return itemsDuringWeekend;
    }

    public Set<Customer> getWomenCustomers(Set<Customer> customers) {
        Set<Customer> women = new HashSet<>();
        for (Customer customer : customers) {
            if (customer.getGender().equals(Gender.FEMALE)) {
                women.add(customer);
            }
        }
        return women;
    }

//    public List<Integer> getAllLastPurchases(Set<Customer> customers) {
//        List<Integer> lastPurchases = new ArrayList<>();
//        for (Customer customer : customers) {
//            lastPurchases.addAll(customer.getLastPurchases());
//        }
//        return lastPurchases;
//    }


}
