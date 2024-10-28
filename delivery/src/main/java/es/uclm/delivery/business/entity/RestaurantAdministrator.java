package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RestaurantAdministrator {
    @Id
    @Column
    private String id_administrator;

    @Column
    private String id_usuary;

    @Column
    private String password;

    public RestaurantAdministrator(String id_administrator, String id_usuary, String password) {
        this.id_administrator = id_administrator;
        this.id_usuary = id_usuary;
        this.password = password;
    }

    public RestaurantAdministrator() {

    }

    public String getId_administrator() {
        return id_administrator;
    }

    public void setId_administrator(String id_administrator) {
        this.id_administrator = id_administrator;
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

    @Override
    public String toString() {
        return String.format("RestaurantAdministrator [id_administrator=%s, id_usuary=%s, password=%s]", id_administrator, id_usuary, password);
    }
    
}
