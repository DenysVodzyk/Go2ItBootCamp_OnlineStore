package Entity;

import java.time.LocalDate;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDate dateOfLastUpdate;

    public Item(int id, int code) {
        this.id = id;
        this.code = code;
    }

    public Item(int id, String title, int code, String producer, LocalDate dateOfLastUpdate) {
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

    public LocalDate getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(LocalDate dateOfLastUpdate) {
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
}
