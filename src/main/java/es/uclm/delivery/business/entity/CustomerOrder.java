package es.uclm.delivery.business.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {
    @Id
    private int orderNumber;

    @Column(nullable = false)
    private String status;

    @OneToOne(mappedBy = "customerOrder",cascade = CascadeType.ALL)
    private DeliveryService deliveryService;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idClient")
    private Client client;


    public CustomerOrder() {
    }

    public CustomerOrder(String status, DeliveryService deliveryService, Client client) {
        this.status = status;
        this.deliveryService = deliveryService;
        this.client = client;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public DeliveryService getDeliveryService() {return deliveryService;}
    public void setDeliveryService(DeliveryService deliveryService) {this.deliveryService = deliveryService;}

    @Override
    public String toString() {
        return String.format("CustomerOrder [order_number=%s, status=%s]", orderNumber, status);
    }
}
