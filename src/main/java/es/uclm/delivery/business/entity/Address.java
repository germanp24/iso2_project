package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_address;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

    @Column
    private String floorNumber;

    @Column(nullable = false)
    private String locality;

    @OneToOne
    @JoinColumn(name = "order_number", referencedColumnName = "orderNumber", nullable = false)
    private CustomerOrder customerOrder;

    public Address() {
    }

    public Address(String street, int number, String floorNumber, String locality, CustomerOrder customerOrder) {
        this.street = street;
        this.number = number;
        this.floorNumber = floorNumber;
        this.locality = locality;
        this.customerOrder = customerOrder;
    }

    // Getters y Setters

    public void setStreet(String street) {
        this.street = street;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return String.format("Address [street=%s, number=%d, floorNumber=%s, locality=%s, customerOrder=%s]",
                street, number, floorNumber, locality, customerOrder != null ? customerOrder.getOrderNumber() : "N/A");
    }

}
