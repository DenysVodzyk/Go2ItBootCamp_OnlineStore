package utils;

import entity.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver {

    public static final String ITEM_FILE_HEADER = "id;title;code;producer;dateOfLastUpdate";

    public static void saveIntoFile(List<Item> items, String filePathWithName, String fileHeader) {

        try (FileWriter fileWriter = new FileWriter(filePathWithName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.append(fileHeader);
            bufferedWriter.append("\n");

            for (Item item : items) {
                String[] result = {String.valueOf(item.getId()),
                        item.getTitle(),
                        String.valueOf(item.getCode()),
                        item.getProducer(),
                        String.valueOf(item.getDateOfLastUpdate())};

                bufferedWriter.append(String.join(",", result));
                bufferedWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
