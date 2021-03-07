import entity.Customer;
import entity.Gender;
import entity.Item;
import entity.Order;
import service.*;
import utils.Constant;
import utils.FileSaver;

import java.time.LocalDate;
import java.util.List;

public class Execute {

    private ItemService itemService;
    private CustomerService customerService;
    private OrderService orderService;
    private ReportService reportService;
    private FileReaderService fileReaderService;

    public Execute() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
        this.customerService = new CustomerService();
        this.orderService = new OrderService();
        this.reportService = new ReportService();
        this.fileReaderService = new FileReaderService();
    }

    public void execute() {
        //Fetched data from .csv files to Java
        List<Item> itemsJava = fileReaderService.parseAllItems(Constant.ITEM_FILE_PATH);
        List<Customer> customersJava = fileReaderService.parseAllCustomers(Constant.CUSTOMER_FILE_PATH);
        List<Order> ordersJava = fileReaderService.parseAllOrders(Constant.CUSTOMER_FILE_PATH);

        //add all items/customers/orders to DB
//        itemService.save(itemsJava);
//        customerService.save(customersJava);
//        orderService.save(ordersJava);

        //Data from db
        List<Customer> customersSQL = customerService.getAll();
        List<Order> ordersSQL = orderService.getAll();
        List<Item> allItemsFromAllOrders = orderService.getAllItemsFromAllOrders();


        System.out.println("Most popular items among women:");
        reportService.getTopRankedItemsBasedOnGender(customersSQL, Gender.FEMALE, true).forEach(System.out::println);
        System.out.println();

        System.out.println("Most popular items during certain time interval:");
        LocalDate startDate = LocalDate.of(2017, 6, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 16);
        reportService.getTopRankedItemsDuringTimeInterval(ordersSQL, startDate, endDate, true).forEach(System.out::println);
        System.out.println();

        System.out.println("Most popular 3 items among all orders. Displayed and saved into file:");
        List<Item> mostPopularItems = reportService.getFirstRankedItems(allItemsFromAllOrders, true, 3);
        mostPopularItems.forEach(System.out::println);
        FileSaver.saveIntoFile(mostPopularItems, "OnlineStore/src/primaryItems.csv", Constant.ITEM_FILE_HEADER);
        System.out.println();

        System.out.println("Least popular 3 items among all orders. Displayed and saved into file:");
        List<Item> leastPopularItems = reportService.getFirstRankedItems(allItemsFromAllOrders, false, 3);
        leastPopularItems.forEach(System.out::println);
        FileSaver.saveIntoFile(leastPopularItems, "OnlineStore/src/candidateToRemove.csv", Constant.ITEM_FILE_HEADER);
        System.out.println();

    }
}
