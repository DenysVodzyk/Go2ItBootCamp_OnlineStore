package Service;

import java.util.*;

public class ReportService {

    //put all passed items into map, where key is the item and value is the number of occurrences
    public Map<Integer, Integer> itemIntoMap(List<Integer> items) {
        Map<Integer, Integer> itemMap = new HashMap<>();
        for (int i : items) {
            Integer count = itemMap.get(i);
            itemMap.put(i, count != null ? count + 1 : 1);
        }
        return itemMap;
    }

    //output is the list of item starting with the most popular item
    public List<Integer> mostPopularItems(List<Integer> items) {
        Map<Integer, Integer> itemMap = itemIntoMap(items);
        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
        List<Integer> mostPopularItems = new ArrayList<>();

        itemMapToSort.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        itemMapToSort.forEach(s -> {
            mostPopularItems.add(s.getKey());
        });
        return mostPopularItems;
    }

    //output is the list of item starting with the least popular item
    public List<Integer> leastPopularItems(List<Integer> items) {
        Map<Integer, Integer> itemMap = itemIntoMap(items);
        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
        List<Integer> leastPopularItems = new ArrayList<>();

        itemMapToSort.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        itemMapToSort.forEach(s -> {
            leastPopularItems.add(s.getKey());
        });
        return leastPopularItems;
    }


}
