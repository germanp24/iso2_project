package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    private Client client;
    private Usuary usuary;

    @BeforeEach
    void setUp() {
        usuary = new Usuary("password", "email@example.com", "CLIENT");
        client = new Client("12345678A", "John", "Doe", "Smith", usuary);
    }

    @Test
    void testGetDni() {
        assertEquals("12345678A", client.getDni());
    }

    @Test
    void testSetDni() {
        client.setDni("87654321B");
        assertEquals("87654321B", client.getDni());
    }

    @Test
    void testGetName() {
        assertEquals("John", client.getName());
    }

    @Test
    void testSetName() {
        client.setName("Jane");
        assertEquals("Jane", client.getName());
    }

    @Test
    void testGetSurnames_M() {
        assertEquals("Smith", client.getSurnames_M());
    }

    @Test
    void testSetSurnames_M() {
        client.setSurnames_M("Johnson");
        assertEquals("Johnson", client.getSurnames_M());
    }

    @Test
    void testGetSurnames_F() {
        assertEquals("Doe", client.getSurnames_F());
    }

    @Test
    void testSetSurnames_F() {
        client.setSurnames_F("Brown");
        assertEquals("Brown", client.getSurnames_F());
    }

    @Test
    void testGetUsuary() {
        assertEquals(usuary, client.getUsuary());
    }

    @Test
    void testSetUsuary() {
        Usuary newUsuary = new Usuary("newpassword", "newemail@example.com", "CLIENT");
        client.setUsuary(newUsuary);
        assertEquals(newUsuary, client.getUsuary());
    }

    @Test
    void testToString() {
        String expected = "Client [dni=12345678A ,name=John, surnames_M=Smith, surnames_F=Doe]";
        assertEquals(expected, client.toString());
    }
}