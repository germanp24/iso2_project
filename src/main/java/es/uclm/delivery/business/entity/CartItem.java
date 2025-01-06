package es.uclm.delivery.business.entity;

public class CartItem {
    private MenuItem menuItem;
    private int quantity;

    // Constructor
    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Getters y setters
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Método para obtener el precio total
    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}
