package com.cabaret.Orders;

import com.cabaret.Order_Item.Order_Item;
import com.cabaret.Tables.Tables;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Orders")
@Table(name = "orders")
public class Orders {
   @Id
   @SequenceGenerator(
           name = "order_id_sequence",
           sequenceName = "order_id_sequence",
           allocationSize = 1
   )
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "order_sequence"
   )
    private long id;
    private boolean isActive;
    private LocalDateTime dateTime;

    @OneToMany(
            mappedBy = "orders",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order_Item> orderItemList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Tables tables;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && isActive == orders.isActive && Objects.equals(dateTime, orders.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, dateTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Orders() {
    }

    public Orders(long id, boolean isActive, LocalDateTime dateTime) {
        this.id = id;
        this.isActive = isActive;
        this.dateTime = dateTime;
    }
}
