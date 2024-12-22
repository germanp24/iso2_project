package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador técnico de Address

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number; // Número de casa/piso como entero

    @Column
    private String floorNumber; // Campo opcional para el número del piso

    @Column(nullable = false)
    private String locality;

    @OneToOne
    @JoinColumn(name = "order_number", referencedColumnName = "orderNumber", nullable = false)
    private CustomerOrder customerOrder; // Relación unidireccional con CustomerOrder

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
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public String toString() {
        return String.format("Address [street=%s, number=%d, floorNumber=%s, locality=%s, customerOrder=%s]",
                street, number, floorNumber, locality, customerOrder != null ? customerOrder.getOrderNumber() : "N/A");
    }
}
