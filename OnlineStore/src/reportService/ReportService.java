package reportService;

import entity.Customer;
import entity.Gender;
import entity.Item;
import entity.Order;
import service.CustomerService;
import service.ItemService;

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
    public List<Item> getPopularItemsByGender(List<Customer> customers, Gender gender, boolean isPopular) {
        List<Customer> customerByGender = customerService.getCustomersByGender(customers, gender);
        List<Item> itemsByGender = new ArrayList<>();

        customerByGender.forEach(customer -> itemsByGender.addAll(customer.getItems()));

        return getPopularItems(itemsByGender, isPopular);
    }

    public List<Item> getPopularItemsDuringTimeInterval(List<Order> orders, LocalDate startDate, LocalDate endDate, boolean isPopular) {
        List<Item> itemsDuringTimeInterval = getItemsDuringTimeInterval(orders, startDate, endDate);
        return getPopularItems(itemsDuringTimeInterval, isPopular);
    }

    public List<Item> getPopularItems(List<Item> items, boolean isPopular) {
        List<Integer> ratedItemListCode = getRankedItemIds(items, isPopular);
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
    public List<Integer> getRankedItemIds(List<Item> items, boolean isPopular) {
        List<Integer> itemsCode = new ArrayList<>();

        for (Item item : items) {
            itemsCode.add(item.getId());
        }

        Map<Integer, Integer> itemMap = itemIntoMap(itemsCode);
        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
        List<Integer> itemsSorted = new ArrayList<>();

        if (isPopular) {
            itemMapToSort.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        } else {
            itemMapToSort.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        }

        itemMapToSort.forEach(s -> itemsSorted.add(s.getKey()));
        return itemsSorted;
    }

    //put all passed items into map, where key is the item and value is the number of occurrences
    public Map<Integer, Integer> itemIntoMap(List<Integer> items) {
        Map<Integer, Integer> itemMap = new HashMap<>();
        for (int i : items) {
            Integer count = itemMap.get(i);
            itemMap.put(i, count != null ? count + 1 : 1);
        }
        return itemMap;
    }

//2021-03-01
//    private Set<Customer> customers = new HashSet<>();
//    private Map<List<Integer>, LocalDate> items = new HashMap<>();
//
//
//    public List<Integer> getItemsDuringParticularWeekend(LocalDate startDate) {
//        LocalDate endDate = startDate.plusDays(1);
//        List<Integer> itemsDuringWeekend = new ArrayList<>();
//
//        for (Map.Entry<List<Integer>, LocalDate> entry : items.entrySet()) {
//            if (entry.getValue().equals(startDate) || entry.getValue().equals(endDate)) {
//                for (Integer item : entry.getKey()) {
//                    itemsDuringWeekend.add(item);
//                }
//            }
//        }
//        return itemsDuringWeekend;
//    }


//    //output is the list of item starting with the least popular item
//    public List<Integer> leastPopularItems(List<Integer> items) {
//        Map<Integer, Integer> itemMap = itemIntoMap(items);
//        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
//        List<Integer> leastPopularItems = new ArrayList<>();
//
//        itemMapToSort.sort(Comparator.comparing(Map.Entry::getValue));
//
//        itemMapToSort.forEach(s -> {
//            leastPopularItems.add(s.getKey());
//        });
//        return leastPopularItems;
//    }


}
