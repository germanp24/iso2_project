package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Usuary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuary;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

    @OneToMany(mappedBy = "usuary", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Client> client = new ArrayList<>();

    @OneToMany(mappedBy = "usuary", cascade = CascadeType.ALL)
    private List<DeliveryMan> deliveryMan = new ArrayList<>();

    @OneToMany(mappedBy = "usuary", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<RestaurantAdministrator> restaurantAdministrator =  new ArrayList<>();

    public Usuary() {

    }

    public Usuary(String password, String email, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }


    public Long getIdUsuary() {
        return idUsuary;
    }
    public void setIdUsuary(Long idUsuary) {
        this.idUsuary = idUsuary;
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

    public List<Client> getClient() {return client;}
    public void setClient(List<Client> client) {this.client = client;}

    public List<DeliveryMan> getDeliveryMan() {return deliveryMan;}
    public void setDeliveryMan(List<DeliveryMan> deliveryMan) {this.deliveryMan = deliveryMan;}

    public List<RestaurantAdministrator> getRestaurantAdministrator() {
        return restaurantAdministrator;
    }
    public void setRestaurantAdministrator(List<RestaurantAdministrator> restaurantAdministrator) {this.restaurantAdministrator = restaurantAdministrator;}

    @Override
    public String toString() {
        return String.format("Usuary [email=%s, id_usuary=%s, password=%s, role=%s]", email, idUsuary, password, role);
    }

}