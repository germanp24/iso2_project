package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuary {
    @Id
    private String id_usuary;

    @Column
    private String password;

    @Column
    private String email;
    
    @Column
    private String role;

    public Usuary() {
        
    }

    public Usuary(String password, String email, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format( "Usuary [id_usuary=%s, password=%s, email=%s, role=%s]", id_usuary, password, email, role);
    }

}