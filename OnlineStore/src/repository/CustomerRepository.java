package repository;

import entity.Customer;
import entity.Gender;
import utils.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public void add(Customer customer) {
        String name = customer.getName();
        java.sql.Date dob = java.sql.Date.valueOf(customer.getDateOfBirth());
        String address = customer.getAddress();
        String gender = customer.getGender().toString();
        String phoneNumber = customer.getPhoneNumber();

        if (getByName(name) == null) {
            String sql = "INSERT INTO customer(name, dob, address, gender, phoneNumber) VALUES (?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, name);
                stm.setDate(2, dob);
                stm.setString(3, address);
                stm.setString(4, gender);
                stm.setString(5, phoneNumber);
                stm.executeUpdate();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Customer getById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String address = rs.getString("address");
                String genderString = rs.getString("gender");
                Gender gender = Gender.getGender(genderString);
                String phoneNumber = rs.getString("phoneNumber");

                customer = new Customer(name, dob, address, gender, phoneNumber, null, null);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public Customer getByName(String customerName) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, customerName.toLowerCase());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String address = rs.getString("address");
                String genderString = rs.getString("gender");
                Gender gender = Gender.getGender(genderString);
                String phoneNumber = rs.getString("phoneNumber");

                customer = new Customer(name, dob, address, gender, phoneNumber, null, null);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                String address = rs.getString("address");
                String genderString = rs.getString("gender");
                Gender gender = Gender.getGender(genderString);
                String phoneNumber = rs.getString("phoneNumber");

                customers.add(new Customer(name, dob, address, gender, phoneNumber, null, null));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    public int getId(Customer customer) {
        int id = 0;
        String sql = "SELECT * FROM customer WHERE name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, customer.getName().toLowerCase());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

}
