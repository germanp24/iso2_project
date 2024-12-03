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
    private String idAdministrator;

    @Column
    private String imageUrl;

    @Column
    private String street;

    @Column
    private String locality;

    public Restaurant(String cif, String name, String idAdministrator, String imageUrl, String street,
            String locality) {
        this.cif = cif;
        this.name = name;
        this.idAdministrator = idAdministrator;
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

    public String getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(String idAdministrator) {
        this.idAdministrator = idAdministrator;
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
                cif, name, idAdministrator, imageUrl, street, locality);
    }
}