package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Restaurant {
    @Id
    @Column
    private String cif;

    @Column
    private String name;

    @Column
    private String id_administrator;

    @Column
    private String imageUrl;

    @Column
    private String street;

    @Column
    private String locality;

    public Restaurant(String cif, String name, String id_administrator, String imageUrl, String street, String locality) {
        this.cif = cif;
        this.name = name;
        this.id_administrator = id_administrator;
        this.imageUrl = imageUrl;
        this.street = street;
        this.locality = locality;
    }

    public Restaurant() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_administrator() {
        return id_administrator;
    }

    public void setId_administrator(String id_administrator) {
        this.id_administrator = id_administrator;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return String.format("Restaurant [cif=%s, name=%s, id_administrator=%s, imageUrl=%s, street=%s, locality=%s]", 
            cif, name, id_administrator, imageUrl, street, locality);
    }
}