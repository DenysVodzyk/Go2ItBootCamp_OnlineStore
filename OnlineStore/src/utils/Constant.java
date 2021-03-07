package utils;

import java.time.format.DateTimeFormatter;

public class Constant {
    public static final DateTimeFormatter DOB_FORMAT = DateTimeFormatter.ofPattern("d MMMM yyyy");
    public static final DateTimeFormatter LAST_PURCHASE_DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");
    public static final DateTimeFormatter LAST_UPDATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");

    public static final String ITEM_FILE_HEADER = "id;title;code;producer;dateOfLastUpdate";

    public static final String ITEM_FILE_PATH = "Items.csv";
    public static final String CUSTOMER_FILE_PATH = "Customers.csv";
}
