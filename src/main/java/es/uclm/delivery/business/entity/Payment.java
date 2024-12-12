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
    private long orderNumber;

    @Column
    private Date transactionDate;

    public Payment() {

    }

    public Payment(UUID transactionId, Date transactionDate, long orderNumber) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.orderNumber = orderNumber;
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

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return String.format("MenuItem [transactionId=%s, order_number=%s, transactionDate=%s]", transactionId,
                orderNumber, transactionDate);
    }

}
