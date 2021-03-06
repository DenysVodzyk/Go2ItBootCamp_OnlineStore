package entity;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String gender) {
        if (gender.toLowerCase().equals("female")) {
            return FEMALE;
        }
        return MALE;
    }
}
