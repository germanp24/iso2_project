package es.uclm.delivery.business.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class DeliveryService {
    @Id
    private String deliveryNumber;

    @Column
    private LocalDateTime deliveryDate;

    @Column
    private LocalDateTime deliveryDatePickup;

    @Column(nullable = false)
    private String pickupAddress;

    @Column(nullable = false)
    private String destination;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDeliveryMan")
    private DeliveryMan deliveryMan;

    @OneToOne
    @JoinColumn(name = "orderNumber")
    private CustomerOrder customerOrder;

    public DeliveryService() {
    }

    public DeliveryService(LocalDateTime deliveryDate, LocalDateTime deliveryDatePickup, String pickupAddress,
           String destination, DeliveryMan deliveryMan, CustomerOrder customerOrder) {
        this.deliveryDate = deliveryDate;
        this.deliveryDatePickup = deliveryDatePickup;
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.deliveryMan = deliveryMan;
        this.customerOrder = customerOrder;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }
    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getDeliveryDatePickup() {return deliveryDatePickup;}
    public void setDeliveryDatePickup(LocalDateTime deliveryDatePickup) {this.deliveryDatePickup = deliveryDatePickup;}

    public String getPickupAddress() {return pickupAddress;}
    public void setPickupAddress(String pickupAddress) {this.pickupAddress = pickupAddress;}

    public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}

    public DeliveryMan getDeliveryMan() {return deliveryMan;}
    public void setDeliveryMan(DeliveryMan deliveryMan) {this.deliveryMan = deliveryMan;}

    public CustomerOrder getCustomerOrder() {return customerOrder;}
    public void setCustomerOrder(CustomerOrder customerOrder) {this.customerOrder = customerOrder;}

    @Override
    public String toString() {
        return String.format(
                "DeliveryService [delivery_date=%s, deliveryDatePickup=%s, pickupAddress=%s, destination=%s]",
                deliveryDate, deliveryDatePickup, pickupAddress, destination);
    }

}
