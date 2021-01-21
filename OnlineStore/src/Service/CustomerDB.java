package Service;

import Entity.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomerDB {
    public static void main(String[] args) {
        readCustomerFromFile();
    }

    public static void readCustomerFromFile() {
        String path = "C:\\Users\\vodzy\\IdeaProjects\\Go2ItBootCamp_OnlineStore\\Customers.csv";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
           // reader.readLine();
            String input;
            while ((input = reader.readLine()) != null) {
                String[] customerInfo = input.split(";");
                System.out.println(customerInfo[1]);
                System.out.println(customerInfo[0] + "---" + customerInfo [1] + "---" + customerInfo[2] + "---" + customerInfo[3] + "---" + customerInfo[4] + "---" + customerInfo[5] + customerInfo[6] );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
