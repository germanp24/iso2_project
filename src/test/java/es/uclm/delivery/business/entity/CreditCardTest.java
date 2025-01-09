package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditCardTest {

    private CreditCard creditCard;
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("12345678A", "John", "Doe", "Smith", new Usuary("password", "email@example.com", "CLIENT"));
        creditCard = new CreditCard("1234567890123456", "12/23", "123", client);
    }

    @Test
    void testGetCardNumber() {
        assertEquals("1234567890123456", creditCard.getCardNumber());
    }

    @Test
    void testSetCardNumber() {
        creditCard.setCardNumber("6543210987654321");
        assertEquals("6543210987654321", creditCard.getCardNumber());
    }

    @Test
    void testGetCardExpiry() {
        assertEquals("12/23", creditCard.getCardExpiry());
    }

    @Test
    void testSetCardExpiry() {
        creditCard.setCardExpiry("11/24");
        assertEquals("11/24", creditCard.getCardExpiry());
    }

    @Test
    void testGetCardCvv() {
        assertEquals("123", creditCard.getCardCvv());
    }

    @Test
    void testSetCardCvv() {
        creditCard.setCardCvv("456");
        assertEquals("456", creditCard.getCardCvv());
    }

    @Test
    void testGetClient() {
        assertEquals(client, creditCard.getClient());
    }

    @Test
    void testSetClient() {
        Client newClient = new Client("87654321B", "Jane", "Doe", "Johnson", new Usuary("newpassword", "newemail@example.com", "CLIENT"));
        creditCard.setClient(newClient);
        assertEquals(newClient, creditCard.getClient());
    }

    @Test
    void testToString() {
        String expected = String.format("CreditCard [cardNumber=%s, cardExpiry=%s, cardCvv=%s, clientId=%s]",
                "1234567890123456", "12/23", "123", client.getIdClient());
        assertEquals(expected, creditCard.toString());
    }
}