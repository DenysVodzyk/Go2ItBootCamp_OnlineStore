package Service;

import Entity.Customer;
import Entity.Gender;
import Entity.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private ReadFileService fileService = new ReadFileService();
    private ItemService itemService = new ItemService();

    public List<Customer> getCustomers(String customersFilePath, String itemsFilePath) {
        List<String> fileData = fileService.readFromFile(customersFilePath);
        List<Customer> customers = new ArrayList<>();

        for (String customer : fileData) {
            String[] customerData = customer.split("[;:]");
            String name = customerData[0];
            LocalDate dOB = getDate(customerData[1], "d MMMM yyyy");
            String address = trimFirstLastCharacter(customerData[2]);
            Gender gender = getGender(customerData[3]);
            String phoneNumber = customerData[4];
            List<Item> lastPurchases = getLastPurchases(customerData[5], itemsFilePath);
            LocalDate dateOfLastPurchase = getDate(customerData[6], "M/d/yyyy");

            if ((phoneNumber.isEmpty()))
                customers.add(new Customer(name, dOB, address, gender, lastPurchases, dateOfLastPurchase));
            else
                customers.add(new Customer(name, dOB, address, gender, phoneNumber, lastPurchases, dateOfLastPurchase));
        }
        return customers;
    }

    public LocalDate getDate(String date, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, formatter);
    }

    public String trimFirstLastCharacter(String input) {
        return input.substring(1, input.length() - 1);
    }

    public Gender getGender(String gender) {
        if (gender.toLowerCase().equals("female")) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    //fetch last purchases done by customer. Based on the id of the item, find item from existing list of items and add it to item collection.
    public List<Item> getLastPurchases(String purchases, String itemListPath) {
        List<Item> lastPurchases = new ArrayList<>();
        String purchase;

        purchase = (purchases.length() <= 2) ? purchases : trimFirstLastCharacter(purchases);
        for (String item : purchase.split(",")) {
            int itemId = Integer.parseInt(item);
            lastPurchases.add(itemService.getItemById(itemId, itemListPath));
        }
        return lastPurchases;
    }

}
