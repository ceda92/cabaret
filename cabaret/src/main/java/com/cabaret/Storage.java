package com.cabaret;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Storage {

    @Id
    @SequenceGenerator(
            name = "storage_id_sequence",
            sequenceName = "storage_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "storage_id_sequence"
    )
    private Integer id;
    private  String name;
    private  String type;
    private Integer amount;

    public Storage(Integer id, String name, String type, Integer amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Storage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage storage)) return false;
        return Objects.equals(id, storage.id) && Objects.equals(name, storage.name) && Objects.equals(type, storage.type) && Objects.equals(amount, storage.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, amount);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
