package es.uclm.delivery.business.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerOrder {
    @Id
    @Column
    private int order_number;

    @Column
    private String dni;

    @Column
    private Date date;

    @Column
    private String ordered_food;

    public CustomerOrder() {

    }

    public CustomerOrder(int order_number, String dni, Date date, String ordered_food) {
        this.order_number = order_number;
        this.dni = dni;
        this.date = date;
        this.ordered_food = ordered_food;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
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

    public String getOrdered_food() {
        return ordered_food;
    }

    public void setOrdered_food(String ordered_food) {
        this.ordered_food = ordered_food;
    }

}
