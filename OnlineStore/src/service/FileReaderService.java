package service;

import entity.Customer;
import entity.Gender;
import entity.Item;
import entity.Order;
import utils.Constant;
import utils.DataEditor;
import utils.FileReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
    private ItemService itemService;

    public FileReaderService() {
        init();
    }

    public void init() {
        this.itemService = new ItemService();
    }

    public List<Customer> parseAllCustomers(String customersFilePath) {
        List<String> fileData = FileReader.readFromFile(customersFilePath);
        List<Customer> customers = new ArrayList<>();

        for (String customer : fileData) {
            String[] customerData = customer.split("[;:]");
            String name = customerData[0];
            LocalDate dOB = LocalDate.parse(customerData[1], Constant.DOB_FORMAT);
            String address = DataEditor.trimFirstLastCharacter(customerData[2]);
            Gender gender = Gender.getGender(customerData[3]);
            String phoneNumber = customerData[4];
            String listOfItemIds = customerData[5];
            LocalDate dateOfLastPurchase = LocalDate.parse(customerData[6], Constant.LAST_PURCHASE_DATE_FORMAT);
            List<Item> lastPurchases = new ArrayList<>();

            String purchase = (listOfItemIds.length() <= 2) ? listOfItemIds : DataEditor.trimFirstLastCharacter(listOfItemIds);
            for (String item : purchase.split(",")) {
                int itemId = Integer.parseInt(item);
                lastPurchases.add(itemService.getById(itemId));
            }

            if ((phoneNumber.isEmpty())) {
                customers.add(new Customer(name, dOB, address, gender, "", lastPurchases, dateOfLastPurchase));
            } else {
                customers.add(new Customer(name, dOB, address, gender, phoneNumber, lastPurchases, dateOfLastPurchase));
            }
        }
        return customers;
    }

    public List<Order> parseAllOrders(String customersFilePath) {
        List<Order> orders = new ArrayList<>();
        List<Customer> customers = parseAllCustomers(customersFilePath);

        for (int i = 0; i < customers.size(); i++) {
            Order order = customers.get(i).getOrder();
            order.setId(i + 1);
            orders.add(order);
        }
        return orders;
    }

    public List<Item> parseAllItems(String filePath) {
        List<String> fileData = FileReader.readFromFile(filePath);
        List<Item> items = new ArrayList<>();

        for (String item : fileData) {
            String[] itemData = item.split(";");
            int id = Integer.parseInt(itemData[0]);
            String title = itemData[1];
            int code = Integer.parseInt(itemData[2]);
            String producer = itemData[3];
            LocalDateTime lastUpdate = LocalDateTime.parse(itemData[4], Constant.LAST_UPDATE_FORMAT);
            items.add(new Item(id, title, code, producer, lastUpdate));
        }
        return items;
    }
}
