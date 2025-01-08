package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

@Entity
public class CreditCard {

    @Id
    @Column
    private String cardNumber;

    @Column
    private String cardExpiry;

    @Column
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    public CreditCard() {}

    public CreditCard(String cardNumber, String cardExpiry, String cardCvv, Client client) {
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cardCvv = cardCvv;
        this.client = client;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return String.format("CreditCard [cardNumber=%s, cardExpiry=%s, cardCvv=%s, clientId=%s]",
                cardNumber, cardExpiry, cardCvv, (client != null ? client.getIdClient() : "No Client"));
    }

}
