package es.uclm.delivery.business.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RestaurantAdministrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;

    @Column
    private String dni;    

    @Column
    private String name;

    @Column
    private String surnames;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    public RestaurantAdministrator() {

    }

    public RestaurantAdministrator(String dni, String name, String surnames, Usuary usuary) {
        this.dni = dni;
        this.name = name;
        this.surnames = surnames;
        this.usuary = usuary;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public Usuary getUsuary() {
        return usuary;
    }

    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    @Override
    public String toString() {
        return String.format( "RestaurantAdministrator [dni=%s, id_admin=%s, name=%s, surnames=%s]", dni, id_admin, name, surnames);
    }
}