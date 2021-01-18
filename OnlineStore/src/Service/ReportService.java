package Service;

import java.util.*;

public class ReportService {

    //output is the list of item starting with the most popular item
    public void mostPopularItems(List<Integer> items) {
        Set<Integer> mostPopularItems = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(items);

        for (int i : items) {
            Integer count = map.get(i);
            map.put(i, count != null ? count + 1 : 1);
        }

        for (Integer name : map.keySet()) {
            System.out.println(name + " : " + map.get(name));
        }


//        List<Integer> max = Collections.sort(map.entrySet(),
//                new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });


    }




}
