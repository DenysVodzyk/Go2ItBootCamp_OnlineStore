package service;

import entity.Item;
import repository.ItemRepository;
import utils.FileReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final DateTimeFormatter LAST_UPDATE = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
    private ItemRepository itemRepository;

    public ItemService() {
        init();
    }

    private void init() {
        this.itemRepository = new ItemRepository();
    }

    public List<Item> parseAll(String filePath) {
        List<String> fileData = FileReader.readFromFile(filePath);
        List<Item> items = new ArrayList<>();

        for (String item : fileData) {
            String[] itemData = item.split(";");
            int id = Integer.parseInt(itemData[0]);
            String title = itemData[1];
            int code = Integer.parseInt(itemData[2]);
            String producer = itemData[3];
            LocalDateTime lastUpdate = LocalDateTime.parse(itemData[4], LAST_UPDATE);
            items.add(new Item(id, title, code, producer, lastUpdate));
        }
        return items;
    }

    public void addAllToDB(List<Item> items) {
        for (Item item : items) {
            itemRepository.add(item);
        }
    }

    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    public Item getById(int id) {
        return itemRepository.getById(id);
    }

    public List<Item> getItemsById(List<Integer> itemIds) {
        List<Item> items = new ArrayList<>();
        for (Integer itemId : itemIds) {
            items.add(getById(itemId));
        }
        return items;
    }
}