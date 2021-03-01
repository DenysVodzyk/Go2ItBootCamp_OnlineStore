package service;

import entity.Gender;

public class GenderService {

    public static Gender getGender(String gender) {
        if (gender.toLowerCase().equals("female")) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }
}
