import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private List<Item> lastPurchases = new ArrayList<>();
    private LocalDate dateOfLastPurchase;

    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, LocalDate dateOfBirth, String address, Gender gender, String phoneNumber, LocalDate dateOfLastPurchase) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Item> getLastPurchases() {
        return lastPurchases;
    }

    public LocalDate getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastPurchases=" + lastPurchases +
                ", dateOfLastPurchase=" + dateOfLastPurchase +
                '}';
    }
}
