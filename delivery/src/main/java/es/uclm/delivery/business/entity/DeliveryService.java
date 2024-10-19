package es.uclm.delivery.business.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryService {
    @Id
    
    @Column
    private LocalDateTime fechaRecepcion;
    
    @Column
    private LocalDateTime fechaEntrega;

    public DeliveryService(LocalDateTime fechaRecepcion, LocalDateTime fechaEntrega) {
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
    }
    
    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
 