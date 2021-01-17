import Entity.Customer;
import Entity.Gender;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Customer charaP = new Customer("Chara Pastrana", LocalDate.of(1954, 4, 26), "7592 College Dr.Fishers, IN 46037", Gender.FEMALE, "(815) 203-5480", new int[]{2, 4, 12}, LocalDate.of(2017, 6, 1));
        Customer lauraleeS = new Customer("Lauralee Spadaro", LocalDate.of(1954, 4, 26), "9674 Primrose Dr. Crofton, MD 21114", Gender.FEMALE, "(781) 939-5956", new int[]{1, 3}, LocalDate.of(2017, 6, 2));
        Customer billC = new Customer("Bill Connor", LocalDate.of(1963, 7, 7), "353 Lilac Road Malden, MA 02148", Gender.MALE, "(554) 478-8654", new int[]{5, 6, 10}, LocalDate.of(2017, 6, 8));

        System.out.println(charaP.getLastPurchases());

    }
}
