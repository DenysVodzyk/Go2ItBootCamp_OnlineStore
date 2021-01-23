package Service;

import Entity.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomerDB {
    public static void main(String[] args) {
        CustomerDB db = new CustomerDB();
        db.readCustomerFromFile();
    }

    public void readCustomerFromFile() {
        String path = "C:\\Users\\vodzy\\IdeaProjects\\Go2ItBootCamp_OnlineStore\\Customers.csv";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String input;
            while ((input = reader.readLine()) != null) {
                  String[] customerInfo = input.split(";");
                System.out.println(customerInfo[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
