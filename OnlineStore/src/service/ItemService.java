package service;

import entity.Item;
import repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService() {
        init();
    }

    private void init() {
        this.itemRepository = new ItemRepository();
    }

    public void save(List<Item> items) {
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