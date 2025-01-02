package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_menu;

    private String foodName;

    @Column
    private double price;

    @Column
    private String category;

    @Column
    private String imgMenu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuContent> menuContents = new ArrayList<>();

    public MenuItem() {

    }

    public MenuItem( long id_menu, String foodName, double price, String category, Restaurant restaurant, String imgMenu) {
        this.id_menu = id_menu;
        this.foodName = foodName;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
        this.imgMenu = imgMenu;
    }

    public Long getId_menu() {return id_menu;}
    public void setId_menu(Long id_menu) {this.id_menu = id_menu;}

    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgMenu() {return imgMenu;}
    public void setImgMenu(String imgMenu) {this.imgMenu = imgMenu;}

    public Restaurant getRestaurant() {return restaurant;}
    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public List<MenuContent> getMenuContents() {return menuContents;}
    public void setMenuContents(List<MenuContent> menuContents) {this.menuContents = menuContents;}

    @Override
    public String toString() {
        return String.format("MenuItem [id_menu=%s, food_name=%s, price=%s, category=%s, imgMenu=%s]", id_menu, foodName, price, category, imgMenu);
    }

}