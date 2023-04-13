package com.cabaret.Order_Item;

import com.cabaret.Orders.Orders;
import com.cabaret.Product.Product;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Order_Item")
@Table(name = "order_item")
public class Order_Item {
    @Id
    @SequenceGenerator( name = "order_item_id_sequence",
                        sequenceName = "order_item_id_sequence",
                        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_id_sequence"
    )
    private long id;
    private int amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;


    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Override
    public String toString() {
        return "Order_Item{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_Item orderItem = (Order_Item) o;
        return id == orderItem.id && amount == orderItem.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order_Item() {
    }

    public Order_Item(long id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
