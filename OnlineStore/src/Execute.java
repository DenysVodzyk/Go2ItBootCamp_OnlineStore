import entity.Item;
import service.ItemService;

import java.util.List;

public class Execute {

    private ItemService itemService;
    private final String ITEM_FILE_PATH = "OnlineStore/src/inputFiles/Items.csv";

    public Execute() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
    }

    public void execute() {
        List<Item> itemsJava = itemService.parseAll(ITEM_FILE_PATH);
        for (Item item : itemsJava) {
            System.out.println(item);
        }

         itemService.addAllToDB(itemsJava);

        itemService.getAll().forEach(item -> {
            System.out.println(item);
        });

       // System.out.println(itemService.getAll());
      //  System.out.println(itemService.getById(1));
    }

}
