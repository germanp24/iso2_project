package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Usuary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientDAOTest {

    @Autowired
    private ClientDAO clientDAO;

    @Test
    void testSaveAndRetrieveClient() {
        // Crear un cliente y su usuario asociado
        Usuary usuary = new Usuary("password123", "client@example.com", "CLIENT", null, null, null);
        Client client = new Client("John", "Doe", "12345678A", usuary);

        // Guardar el cliente
        Client savedClient = clientDAO.save(client);

        // Verificar que fue guardado correctamente
        assertNotNull(savedClient.getIdClient());
        assertEquals("John", savedClient.getName());
        assertEquals("12345678A", savedClient.getDni());
        assertEquals("client@example.com", savedClient.getUsuary().getEmail());
    }

    @Test
    void testRetrieveClientById() {
        // Crear y guardar un cliente
        Usuary usuary = new Usuary("password123", "client2@example.com", "CLIENT", null, null, null);
        Client client = new Client("Jane", "Smith", "87654321B", usuary);
        Client savedClient = clientDAO.save(client);

        // Buscar el cliente por ID
        Client foundClient = clientDAO.findById(savedClient.getIdClient()).orElse(null);

        // Validar los datos del cliente encontrado
        assertNotNull(foundClient);
        assertEquals("Jane", foundClient.getName());
        assertEquals("client2@example.com", foundClient.getUsuary().getEmail());
    }
}
