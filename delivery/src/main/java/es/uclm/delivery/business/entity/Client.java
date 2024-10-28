package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @Column
    private String dni;

    @Column
    private String id_usuary;

    @Column
    private String name;

    @Column
    private String surnames;

    public Client() {

    }

    public Client(String name, String surnames, String dni, String id_usuary) {
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
        this.id_usuary = id_usuary;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getId_usuary() {
        return id_usuary;
    }

    public void setId_usuary(String id_usuary) {
        this.id_usuary = id_usuary;
    }

    @Override
    public String toString() {
        return String.format("Client [dni=%s, id_usuary=%s, name=%s, surnames=%s]", dni, id_usuary, name, surnames);
    }
}
