package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private int zipcode;

    @Column
    private String town;

    public Address() {

    }

    public Address(String street, String number, String town, int zipcode) {
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.town = town;
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

    public Long getIdAddress() { return idAddress; }
    public void setIdAddress(Long idAddress) {  this.idAddress = idAddress; }

    @Override
    public String toString() {
        return String.format("Address [street=%s, number=%s, town=%s, zipcode=%s, idAddress=%s]"
                , street, number,town , zipcode ,idAddress);
    }

}
