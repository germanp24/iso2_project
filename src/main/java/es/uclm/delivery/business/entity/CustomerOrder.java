package es.uclm.delivery.business.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerOrder {
    @Id
    @Column
    private int orderNumber;

    @Column(nullable = true) // Permitir valores nulos para el DNI
    private String dni;

    @Column
    private Date date;

    @Column
    private String orderedFood;

    public CustomerOrder() {

    }

    public CustomerOrder(int orderNumber, String dni, Date date, String orderedFood) {
        this.orderNumber = orderNumber;
        this.dni = dni;
        this.date = date;
        this.orderedFood = orderedFood;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
        return String.format("CustomerOrder [order_number=%s, dni=%s, date=%s, ordered_food=%s]", orderNumber, dni,
                date, orderedFood);
    }
}
