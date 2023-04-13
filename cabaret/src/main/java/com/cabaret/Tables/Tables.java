package com.cabaret.Tables;

import com.cabaret.Orders.Orders;
import jakarta.persistence.*;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Tables")
@Table(name = "tables")
public class Tables {
    @Id
    @SequenceGenerator(name = "tables_id_sequence",
            sequenceName = "tables_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tables_id_sequence"
    )
    private long id;
    private String tableNumber;

    @OneToMany(
            mappedBy = "tables",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Orders>ordersList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return id == tables.id && Objects.equals(tableNumber, tables.tableNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableNumber);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", tableNumber='" + tableNumber + '\'' +
                '}';
    }

    public Tables() {
    }

    public Tables(long id, String tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
}
