package service;

import entity.Customer;
import entity.Gender;
import entity.Item;
import entity.Order;

import java.time.LocalDate;
import java.util.*;

public class ReportService {
    private CustomerService customerService;
    private ItemService itemService;

    public ReportService() {
        init();
    }

    private void init() {
        this.customerService = new CustomerService();
        this.itemService = new ItemService();
    }

    //pass true - to see most popular items, false - to see least popular items
    public List<Item> getTopRankedItemsBasedOnGender(List<Customer> customers, Gender gender, boolean isPopularAsc) {
        List<Customer> customerByGender = customerService.getCustomersByGender(customers, gender);
        List<Item> itemsByGender = new ArrayList<>();

        customerByGender.forEach(customer -> itemsByGender.addAll(customer.getItems()));

        return getTopRankedItems(itemsByGender, isPopularAsc);
    }

    public List<Item> getTopRankedItemsDuringTimeInterval(List<Order> orders, LocalDate startDate, LocalDate endDate, boolean isPopularAsc) {
        List<Item> itemsDuringTimeInterval = getItemsDuringTimeInterval(orders, startDate, endDate);
        return getTopRankedItems(itemsDuringTimeInterval, isPopularAsc);
    }

    public List<Item> getFirstRankedItems(List<Item> items, boolean isPopular, int numberOfItems) {
        return getTopRankedItems(items, isPopular).subList(0, numberOfItems);
    }

    public List<Item> getTopRankedItems(List<Item> items, boolean isPopular) {
        List<Integer> ratedItemListCode = getTopRankedItemIds(items, isPopular);
        return itemService.getItemsById(ratedItemListCode);
    }

    public List<Item> getItemsDuringTimeInterval(List<Order> orders, LocalDate startDate, LocalDate endDate) {
        List<Item> itemsDuringTimeInterval = new ArrayList<>();

        for (Order order : orders) {
            if (!order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate)) {
                itemsDuringTimeInterval.addAll(order.getItems());
            }
        }
        return itemsDuringTimeInterval;
    }

    //returns the list of items based on the boolean passed (true - popular, false - not popular),
    // starting with the most/least popular item
    private List<Integer> getTopRankedItemIds(List<Item> items, boolean isPopularAsc) {
        List<Integer> itemsCode = new ArrayList<>();

        for (Item item : items) {
            itemsCode.add(item.getId());
        }

        Map<Integer, Integer> itemMap = itemIntoMap(itemsCode);
        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
        List<Integer> itemsSorted = new ArrayList<>();

        if (isPopularAsc) {
            itemMapToSort.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        } else {
            itemMapToSort.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        }

        itemMapToSort.forEach(s -> itemsSorted.add(s.getKey()));
        return itemsSorted;
    }

    //put all passed items into map, where key is the item and value is the number of occurrences
    private Map<Integer, Integer> itemIntoMap(List<Integer> items) {
        Map<Integer, Integer> itemMap = new HashMap<>();
        for (int i : items) {
            Integer count = itemMap.get(i);
            itemMap.put(i, count != null ? count + 1 : 1);
        }
        return itemMap;
    }

}
