package es.uclm.delivery.business.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity

public class Usuary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuary;

    @Column
    private String password;

    @Column
    private String email;
    
    @Column
    private String role;

    @OneToOne(mappedBy = "usuary", cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(mappedBy = "usuary", cascade = CascadeType.ALL)
    private DeliveryMan deliveryMan;

    @OneToOne(mappedBy = "usuary", cascade = CascadeType.ALL)
    private RestaurantAdministrator restaurantAdministrator;

    public Usuary() {
        
    }

    public Usuary(String password, String email, String role, Client client, DeliveryMan deliveryMan, RestaurantAdministrator restaurantAdministrator) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.client = client;
        this.deliveryMan = deliveryMan;
        this.restaurantAdministrator = restaurantAdministrator;

    }

    public Long getId_usuary() {
        return id_usuary;
    }

    public void setId_usuary(Long id_usuary) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public RestaurantAdministrator getRestaurantAdministrator() {
        return restaurantAdministrator;
    }

    public void setRestaurantAdministrator(RestaurantAdministrator restaurantAdministrator) {
        this.restaurantAdministrator = restaurantAdministrator;
    }

    @Override
    public String toString() {
        return String.format( "Usuary [email=%s, id_usuary=%s, password=%s, role=%s]", email, id_usuary, password, role);
    }

}