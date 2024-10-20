package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @Column
    private String order_number;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String complement;

    @Column
    private int zipcode;

    @Column
    private String town;

    public Address() {

    }

    public Address(String street, String number, String complement, int zipcode, String town, String order_number) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipcode = zipcode;
        this.town = town;
        this.order_number = order_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

}
