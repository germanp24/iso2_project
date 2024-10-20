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

    @Column
    private String email;

    public Usuary(String id_usuary, String password, String email) {
        this.id_usuary = id_usuary;
        this.password = password;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}