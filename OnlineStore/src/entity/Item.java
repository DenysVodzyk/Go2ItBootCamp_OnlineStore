package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDateTime dateOfLastUpdate;

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String title, int code, String producer, LocalDateTime dateOfLastUpdate) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.producer = producer;
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDateTime getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(LocalDateTime dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    @Override
    public String toString() {
        return "Entity.Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code=" + code +
                ", producer='" + producer + '\'' +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                code == item.code &&
                Objects.equals(title, item.title) &&
                Objects.equals(producer, item.producer) &&
                Objects.equals(dateOfLastUpdate, item.dateOfLastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, code, producer, dateOfLastUpdate);
    }
}
