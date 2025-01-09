package es.uclm.delivery.business.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderNumber; // orderNumber como clave primaria

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String orderedFood;

    public CustomerOrder() {

    }

    public CustomerOrder(String address, Date date, String orderedFood) {
        this.address = address;
        this.date = date;
        this.orderedFood = orderedFood;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderedFood() {
        return orderedFood;
    }

    public void setOrderedFood(String orderedFood) {
        this.orderedFood = orderedFood;
    }

    @Override
    public String toString() {
        return String.format("CustomerOrder [orderNumber=%s, date=%s, address=%s, orderedFood=%s]", orderNumber, date, address, orderedFood);
    }
}