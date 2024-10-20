package es.uclm.delivery.business.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryService {
    @Id
    @Column
    private String delivery_number;

    @Column
    private String nif;

    @Column
    private String order_number;

    @Column
    private LocalDateTime reception_date;

    @Column
    private LocalDateTime delivery_date;

    public DeliveryService() {

    }

    public DeliveryService(String delivery_number, String nif, String order_number, LocalDateTime reception_date,
            LocalDateTime delivery_date) {
        this.delivery_number = delivery_number;
        this.nif = nif;
        this.order_number = order_number;
        this.reception_date = reception_date;
        this.delivery_date = delivery_date;
    }

    public String getDelivery_number() {
        return delivery_number;
    }

    public void setDelivery_number(String delivery_number) {
        this.delivery_number = delivery_number;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public LocalDateTime getReception_date() {
        return reception_date;
    }

    public void setReception_date(LocalDateTime reception_date) {
        this.reception_date = reception_date;
    }

    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }

}
