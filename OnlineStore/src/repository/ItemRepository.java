package repository;

import entity.Item;
import utils.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    public void add(Item item) {
        String sql = "INSERT INTO item(id, title, code, producer, dateOfLastUpdate) VALUES (?, ?, ?, ?, ?)";
        int id = item.getId();
        String title = item.getTitle();
        int code = item.getCode();
        String producer = item.getProducer();
        java.sql.Timestamp lastUpdate = java.sql.Timestamp.valueOf(item.getDateOfLastUpdate());
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.setString(2, title);
            stm.setInt(3, code);
            stm.setString(4, producer);
            stm.setTimestamp(5, lastUpdate);

            if (!isInDb(id, title, code, producer)) {
                stm.executeUpdate();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isInDb(int id, String title, int code, String producer) {
        boolean result = false;
        String sql = "SELECT * FROM item WHERE id=? AND title=? AND code=? AND producer=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.setString(2, title);
            stm.setInt(3, code);
            stm.setString(4, producer);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result = true;
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Item getById(int id) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE id=" + id;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("id");
                String title = rs.getString("title");
                int code = rs.getInt("code");
                String producer = rs.getString("producer");
                LocalDateTime lastUpdate = rs.getTimestamp("dateOfLastUpdate").toLocalDateTime();
                item = new Item(itemId, title, code, producer, lastUpdate);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return item;
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("id");
                String title = rs.getString("title");
                int code = rs.getInt("code");
                String producer = rs.getString("producer");
                LocalDateTime lastUpdate = rs.getTimestamp("dateOfLastUpdate").toLocalDateTime();
                items.add(new Item(itemId, title, code, producer, lastUpdate));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }

}
