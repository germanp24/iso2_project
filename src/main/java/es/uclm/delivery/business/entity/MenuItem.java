package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MenuItem {
    @Id
    @Column
    private String foodName;

    @Column
    private double price;

    @Column
    private String category;

    public MenuItem() {

    }

    public MenuItem(String foodName, double price, String category) {
        this.foodName = foodName;
        this.price = price;
        this.category = category;
    }

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

    @Override
    public String toString() {
        return String.format("MenuItem [food_name=%s, price=%s, category=%s]", foodName, price, category);
    }

}