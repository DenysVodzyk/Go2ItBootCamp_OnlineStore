import entity.Customer;
import entity.Gender;
import entity.Item;
import entity.Order;
import reportService.ReportService;
import service.CustomerService;
import service.ItemService;
import service.OrderService;
import utils.FileSaver;

import java.time.LocalDate;
import java.util.List;

public class Execute {

    private ItemService itemService;
    private CustomerService customerService;
    private OrderService orderService;
    private ReportService reportService;
    private final String ITEM_FILE_PATH = "OnlineStore/src/inputFiles/Items.csv";
    private final String CUSTOMER_FILE_PATH = "OnlineStore/src/inputFiles/Customers.csv";


    public Execute() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
        this.customerService = new CustomerService();
        this.orderService = new OrderService();
        this.reportService = new ReportService();
    }

    public void execute() {
        //Fetched data from .csv files to Java
        List<Item> itemsJava = itemService.parseAll(ITEM_FILE_PATH);
        List<Customer> customersJava = customerService.parseAll(CUSTOMER_FILE_PATH);
        List<Order> ordersJava = orderService.parseAll(CUSTOMER_FILE_PATH);

        //add all items/customers/orders to DB
        itemService.addAllToDB(itemsJava);
        customerService.addAllToDB(customersJava);
        orderService.addAllToDB(ordersJava);

        //Data from db
        List<Customer> customersSQL = customerService.getAll();
        List<Order> ordersSQL = orderService.getAll();
        List<Item> allItemsFromAllOrders = orderService.getAllItemsFromAllOrders();


        System.out.println("Most popular items among women:");
        reportService.getRankedItemsBasedOnGender(customersSQL, Gender.FEMALE, true).forEach(System.out::println);
        System.out.println();

        System.out.println("Most popular items during certain time interval:");
        LocalDate startDate = LocalDate.of(2017, 6, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 16);
        reportService.getRankedItemsDuringTimeInterval(ordersSQL, startDate, endDate, true).forEach(System.out::println);
        System.out.println();

        System.out.println("Most popular 3 items among all orders. Displayed and saved into file:");
        List<Item> mostPopularItems = reportService.getFirstRankedItems(allItemsFromAllOrders, true, 3);
        mostPopularItems.forEach(System.out::println);
        FileSaver.saveIntoFile(mostPopularItems, "OnlineStore/src/primaryItems.csv", FileSaver.ITEM_FILE_HEADER);
        System.out.println();

        System.out.println("Least popular 3 items among all orders. Displayed and saved into file:");
        List<Item> leastPopularItems = reportService.getFirstRankedItems(allItemsFromAllOrders, false, 3);
        leastPopularItems.forEach(System.out::println);
        FileSaver.saveIntoFile(leastPopularItems, "OnlineStore/src/candidateToRemove.csv", FileSaver.ITEM_FILE_HEADER);
        System.out.println();

    }
}


/*
*
        //items from Java
        itemsJava.forEach(System.out::println);
        System.out.println();

        //add all items to DB
        itemService.addAllToDB(itemsJava);
        //show all items from DB
        itemsSQL.forEach(System.out::println);
        System.out.println();

        //customers from Java
        customersJava.forEach(System.out::println);
        System.out.println();

        //add all customers to DB
        customerService.addAllToDB(customersJava);

        //show all customers to DB
        customersSQL.forEach(System.out::println);
        System.out.println();

        //show customer by ID and name
        System.out.println(customerService.getById(1));
        System.out.println(customerService.getByName("Katy Hoy"));
        System.out.println();

        //orders from Java
        for (Order order : ordersJava) {
            System.out.println(order.getId() + " " + order);
        }
        System.out.println();

        //add all orders to DB
        orderService.addAllToDB(ordersJava);

        //show order by id
        System.out.println(orderService.getById(1));
        System.out.println();

        //show all orders
        ordersSQL.forEach(System.out::println);
        System.out.println();

        //show orders based on item id
        orderService.getByItemId(2).forEach(System.out::println);
        System.out.println();

        //show order based on customer id
        System.out.println(orderService.getByCustomerId(1));
        System.out.println();

        //Show female

        for (Customer customer : customerService.getCustomersByGender(customersSQL, Gender.FEMALE)) {
            for (Item item : customer.getItems()) {
                System.out.print(" " + item.getId());
            }
        }

        System.out.println();
* */