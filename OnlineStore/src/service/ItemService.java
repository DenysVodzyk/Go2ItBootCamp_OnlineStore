package Service;

import Entity.Customer;
import Entity.Item;
import Entity.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private ReadFileService fileService = new ReadFileService();

    public List<Item> getItems(String filePath) {
        List<String> fileData = fileService.readFromFile(filePath);
        List<Item> items = new ArrayList<>();

        for (String item : fileData) {
            String[] itemData = item.split(";");
            int id = Integer.parseInt(itemData[0]);
            String title = itemData[1];
            int code = Integer.parseInt(itemData[2]);
            String producer = itemData[3];
            LocalDate lastUpdate = getDateOfLastUpdate(itemData[4]);

            items.add(new Item(id, title, code, producer, lastUpdate));
        }
        return items;
    }

    public Item getItemById(int id, String filePath) {
        for (Item item : getItems(filePath)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public LocalDate getDateOfLastUpdate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
        return LocalDate.parse(date, formatter);
    }

    public List<Item> getItemsFromCustomers(List<Customer> customers) {
        List<Item> items = new ArrayList<>();
        for (Customer customer : customers) {
            for (Order order : customer.getOrder()) {
                for (Item item : order.getItems()) {
                    items.add(item);
                }
            }
        }
        return items;
    }


}

