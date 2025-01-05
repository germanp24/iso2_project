package es.uclm.delivery.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.*;

@Entity
public class Restaurant {
    @Id
    @Pattern(regexp = "\\d{6}[A-Za-z]", message = "El Cif del restaurante debe contener 6 números seguidos de una letra")
    private String cif;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @Column
    private String street;

    @Column
    private String locality;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menu = new ArrayList<>();

    @ManyToMany(mappedBy = "restaurant")
    private Set<DeliveryMan> deliveryMan = new HashSet<>();

    @ManyToMany(mappedBy = "restaurant")
    private Set<RestaurantAdministrator> restaurantAdministrator = new HashSet<>();

    public Restaurant() {

    }
    public Restaurant(String cif, String name, String imageUrl, String street, String locality) {
        this.cif = cif;
        this.name = name;
        this.imageUrl = imageUrl;
        this.street = street;
        this.locality = locality;
    }


    public String getCif() {
        return cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }

    public List<MenuItem> getMenu(){return menu;}
    public void setMenu(List<MenuItem> menu) {this.menu = menu;}

    public Set<DeliveryMan> getDeliveryMan() {return deliveryMan;}
    public void setDeliveryMan(Set<DeliveryMan> deliveryMan) {this.deliveryMan = deliveryMan;}

    public Set<RestaurantAdministrator> getRestaurantAdministrator() {return restaurantAdministrator;}
    public void setRestaurantAdministrator(Set<RestaurantAdministrator> restaurantAdministrator) {this.restaurantAdministrator = restaurantAdministrator;}

    @Override
    public String toString() {
        return String.format("Restaurant [cif=%s, name=%s, imageUrl=%s, street=%s, locality=%s]", 
            cif, name, imageUrl, street, locality);
    }
}