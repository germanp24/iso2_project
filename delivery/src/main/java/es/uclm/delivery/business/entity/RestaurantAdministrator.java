package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RestaurantAdministrator {
    @Id
    @Column
    private String dni_a;

    @Column
    private String id_admin;

    @Column
    private String name_a;

    @Column
    private String surnames_a;

    public RestaurantAdministrator() {

    }

    public RestaurantAdministrator(String dni_a, String id_admin, String name_a, String surnames_a) {
        this.dni_a = dni_a;
        this.id_admin = id_admin;
        this.name_a = name_a;
        this.surnames_a = surnames_a;
    }

    public String getDni_a() {
        return dni_a;
    }

    public void setDni_a(String dni_a) {
        this.dni_a = dni_a;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public String getSurnames_a() {
        return surnames_a;
    }

    public void setSurnames_a(String surnames_a) {
        this.surnames_a = surnames_a;
    }

    @Override
    public String toString() {
        return String.format("RestaurantAdministrator [dni_a=%s, id_admin=%s, name_a=%s, surnames_a=%s]", dni_a, id_admin, name_a, surnames_a);
    }
}