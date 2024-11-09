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
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;

    @Column
    private String dni;

    @Column
    private String name;

    @Column
    private String surnames;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    public Client() {

    }

    public Client(String name, String surnames, String dni, Usuary usuary) {
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
        this.usuary = usuary;
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

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public Usuary getUsuary() {
        return usuary;
    }

    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    @Override
    public String toString() {
        return String.format("Client [dni=%s, id_client=%s, name=%s, surnames=%s]", dni, id_client, name, surnames);
    }
}
