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

    public Restaurant(String cif, String name, String id_administrator) {
        this.cif = cif;
        this.name = name;
        this.id_administrator = id_administrator;
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

}
