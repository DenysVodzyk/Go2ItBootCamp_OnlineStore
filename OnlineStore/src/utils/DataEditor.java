package utils;

public class DataEditor {

    public static String trimFirstLastCharacter(String input) {
        return input.substring(1, input.length() - 1);
    }
}
