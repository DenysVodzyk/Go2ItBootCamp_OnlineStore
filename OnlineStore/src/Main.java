import entity.Customer;
import entity.Gender;
import entity.Item;
import service.CustomerService;
import service.ItemService;
import service.ReportService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Execute execute = new Execute();

        execute.execute();


/*        String customerFilePath = "C:\\Users\\vodzy\\IdeaProjects\\Go2ItBootCamp_OnlineStore\\Customers.csv";
        String itemFilePath = "C:\\Users\\vodzy\\IdeaProjects\\Go2ItBootCamp_OnlineStore\\Items.csv";

        ItemService service = new ItemService();
        CustomerService customerService = new CustomerService();
        ReportService reportService = new ReportService();

        List<Customer> customers = customerService.getCustomers(customerFilePath, itemFilePath);

        List<Customer> women = customerService.getCustomersByGender(customers, Gender.FEMALE);

        List<Item> WomenItems = service.getItemsFromCustomers(women);

        List<Integer> popularItemsAmongWomen = reportService.isPopularItems(WomenItems, false);

        for (Item item : WomenItems) {
            System.out.print(item.getId() + " ");
        }
        System.out.println("");

        System.out.println(popularItemsAmongWomen);*/


//        Customer charaP = new Customer("Chara Pastrana", LocalDate.of(1954, 4, 26), "7592 College Dr.Fishers, IN 46037", Gender.FEMALE, "(815) 203-5480", new int[]{2, 4, 12}, LocalDate.of(2017, 6, 1));
//        Customer lauraleeS = new Customer("Lauralee Spadaro", LocalDate.of(1954, 4, 26), "9674 Primrose Dr. Crofton, MD 21114", Gender.FEMALE, "(781) 939-5956", new int[]{1, 3}, LocalDate.of(2017, 6, 2));
//        Customer billC = new Customer("Bill Connor", LocalDate.of(1963, 7, 7), "353 Lilac Road Malden, MA 02148", Gender.MALE, "(554) 478-8654", new int[]{5, 6, 10}, LocalDate.of(2017, 6, 8));
//        Customer shauntaM = new Customer("Shaunta Mcgeorge", LocalDate.of(1966, 9, 10), "7710 Glenwood Lane Centreville, VA 20120", Gender.FEMALE, new int[]{12, 14}, LocalDate.of(2017, 6, 10));

//        CollectionService collectionService = new CollectionService();
//        ReportService reportService = new ReportService();
//
//        collectionService.addCustomer(charaP);
//        collectionService.addCustomer(lauraleeS);
//        collectionService.addCustomer(billC);
//        collectionService.addCustomer(shauntaM);
//
//        collectionService.addItem(charaP);
//        collectionService.addItem(lauraleeS);
//        collectionService.addItem(billC);
//        collectionService.addItem(shauntaM);
//
//        System.out.println("All customers: ");
//        Set<Customer> allCustomers = collectionService.getCustomers();
//        System.out.println(allCustomers);
//
//        System.out.println("All women customers: ");
//        Set<Customer> women = collectionService.getWomenCustomers(allCustomers);
//        System.out.println(women);
//
//        System.out.println("All last purchases made by women: ");
//        List<Integer> womenPurchases = collectionService.getAllLastPurchases(women);
//        System.out.println(womenPurchases);
//
//        System.out.println("Goods most popular among women: ");
//        List<Integer> womenPopularPurchases = reportService.mostPopularItems(womenPurchases);
//        System.out.println(womenPopularPurchases);
//
//        System.out.println("Goods most popular during particular weekend: ");
//        List<Integer> allItems = collectionService.getAllLastPurchases(allCustomers);
//        List<Integer> itemsDuringParticularWeekend = collectionService.getItemsDuringParticularWeekend(LocalDate.of(2017, 6, 1));
//        List<Integer> popularItemsDuringWeekend = reportService.mostPopularItems(itemsDuringParticularWeekend);
//        System.out.println(popularItemsDuringWeekend);
//
//


    }
}
