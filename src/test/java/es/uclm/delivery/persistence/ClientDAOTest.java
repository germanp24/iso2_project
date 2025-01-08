package es.uclm.delivery.persistence;
import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.persistence.ClientDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {
    @Autowired
    private ClientDAO clientDAO;

    @BeforeEach
    public void setUp() {
        clientDAO.deleteAll();
    }

    //Probar que el sistema puede manejar datos inválidos (como un dni)
    @Test
    public void testClientWithInvalidDni() {
        Client client = new Client("03955964T", "John", "Doe", "Smith", null); // DNI inválido
        assertThrows(IllegalArgumentException.class, () -> {
            clientDAO.save(client);
        });
    }

    @Test
    public void testClientWithMissingFields() {
        Client client = new Client(null, null, null, null, null); // Campos vacíos
        assertThrows(IllegalArgumentException.class, () -> {
            clientDAO.save(client);
        });
    }

    //Verificar que la búsqueda por email con valor null o vacío

    @Test
    public void testFindByEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            clientDAO.findByUsuary_Email(null);
        });
    }

    @Test
    public void testFindByEmailEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            clientDAO.findByUsuary_Email("");
        });
    }


}