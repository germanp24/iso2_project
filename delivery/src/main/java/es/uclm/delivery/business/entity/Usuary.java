package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuary {
    @Id
    @Column
    private String id_usuary;

    @Column
    private String password;

    public String getId_usuary() {
        return id_usuary;
    }

    public void setId_usuary(String id_usuary) {
        this.id_usuary = id_usuary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuary(String id_usuary, String password) {
        this.id_usuary = id_usuary;
        this.password = password;
    }

}