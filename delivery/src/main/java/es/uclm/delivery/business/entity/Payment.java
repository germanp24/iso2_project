package es.uclm.delivery.business.entity;

import java.sql.Date;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id

    @Column
    private UUID transactionId;
    
    @Column
    private Date transactionDate;
    
    public Payment(UUID transactionId, Date transactionDate) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
}
