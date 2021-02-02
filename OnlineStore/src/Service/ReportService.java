package Service;

import Entity.Item;

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

    //returns the list of items based on the boolean passed (true - popular, false - not popular),
    // starting with the most/least popular item
    public List<Integer> isPopularItems(List<Item> items, boolean truePopular_falseNotPopular) {
        List<Integer> itemsCode = new ArrayList<>();

        for (Item item : items) {
            itemsCode.add(item.getId());
        }

        Map<Integer, Integer> itemMap = itemIntoMap(itemsCode);
        List<Map.Entry<Integer, Integer>> itemMapToSort = new ArrayList<>(itemMap.entrySet());
        List<Integer> itemsSorted = new ArrayList<>();

        if (truePopular_falseNotPopular) {
            itemMapToSort.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        } else {
            itemMapToSort.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        }

        itemMapToSort.forEach(s -> {
            itemsSorted.add(s.getKey());
        });
        return itemsSorted;
    }

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
