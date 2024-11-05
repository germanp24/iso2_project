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
    private long order_number;

    @Column
    private Date transactionDate;

    public Payment() {
        
    }

    public Payment(UUID transactionId, Date transactionDate, long order_number) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.order_number = order_number;
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

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    @Override
    public String toString() {
        return String.format("MenuItem [transactionId=%s, order_number=%s, transactionDate=%s]", transactionId, order_number, transactionDate);
    }

}
