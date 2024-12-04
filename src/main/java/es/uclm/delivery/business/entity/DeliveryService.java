package es.uclm.delivery.business.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryService {
    @Id
    @Column
    private String deliveryNumber;

    @Column
    private String nif;

    @Column
    private String orderNumber;

    @Column
    private LocalDateTime receptionDate;

    @Column
    private LocalDateTime deliveryDate;

    public DeliveryService() {
    }

    public DeliveryService(String deliveryNumber, String nif, String orderNumber, LocalDateTime receptionDate,
            LocalDateTime deliveryDate) {
        this.deliveryNumber = deliveryNumber;
        this.nif = nif;
        this.orderNumber = orderNumber;
        this.receptionDate = receptionDate;
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDateTime receptionDate) {
        this.receptionDate = receptionDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return String.format(
                "DeliveryService [delivery_number=%s, nif=%s, order_number=%s, reception_date=%s, delivery_date=%s]",
                deliveryNumber, nif, orderNumber, receptionDate, deliveryDate);
    }

}
