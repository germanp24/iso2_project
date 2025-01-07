package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

@Entity
public class MenuContent {
    @Id
    private String id_menuContent;

    @Column
    private String contentFood;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_menu")
    private MenuItem menuItem;

    public MenuContent() {}
    public MenuContent(String id_menuContent, String contentFood, MenuItem menuItem) {
        this.id_menuContent = id_menuContent;
        this.contentFood = contentFood;
        this.menuItem = menuItem;
    }

    public String getId_menuContent() {return id_menuContent;}
    public void setId_menuContent(String id_menuContent) {this.id_menuContent = id_menuContent;}

    public String getContentFood() {return contentFood;}
    public void setContentFood(String contentFood) {this.contentFood = contentFood;}

    public MenuItem getMenuItem() {return menuItem;}
    public void setMenuItem(MenuItem menuItem) {this.menuItem = menuItem;}

}

