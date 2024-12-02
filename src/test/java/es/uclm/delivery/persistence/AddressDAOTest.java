package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressDAOTest {

    @Autowired
    private AddressDAO addressDAO;

    @Test
    void testSaveAndRetrieveAddress() {
        // Crear un Address
        Address address = new Address("Main Street", "123", "Springfield", 12345);

        // Guardar en el repositorio
        Address savedAddress = addressDAO.save(address);

        // Validar que fue guardado correctamente
        assertNotNull(savedAddress.getIdAddress());
        assertEquals("Main Street", savedAddress.getStreet());
        assertEquals("123", savedAddress.getNumber());
        assertEquals("Springfield", savedAddress.getTown());
        assertEquals(12345, savedAddress.getZipcode());

        // Recuperar por ID
        Optional<Address> foundAddress = addressDAO.findById(savedAddress.getIdAddress().toString());
        assertTrue(foundAddress.isPresent());
        assertEquals("Main Street", foundAddress.get().getStreet());
    }

    @Test
    void testDeleteAddress() {
        // Crear y guardar un Address
        Address address = new Address("Elm Street", "456", "Shelbyville", 54321);
        Address savedAddress = addressDAO.save(address);

        // Eliminar el Address
        addressDAO.deleteById(savedAddress.getIdAddress().toString());

        // Validar que ya no existe
        Optional<Address> foundAddress = addressDAO.findById(savedAddress.getIdAddress().toString());
        assertFalse(foundAddress.isPresent());
    }

    @Test
    void testFindAllAddresses() {
        // Crear y guardar varios Addresses
        Address addr1 = new Address("Baker Street", "221B", "London", 56789);
        Address addr2 = new Address("Fifth Avenue", "789", "New York", 67890);
        addressDAO.save(addr1);
        addressDAO.save(addr2);

        // Validar que se pueden recuperar todos
        assertEquals(2, addressDAO.findAll().size());
    }

    @Test
    void testUpdateAddress() {

        Address address = new Address("Old Street", "321", "Gotham", 98765);
        Address savedAddress = addressDAO.save(address);


        savedAddress.setTown("Metropolis");
        savedAddress.setZipcode(65432);
        addressDAO.save(savedAddress);


        Optional<Address> updatedAddress = addressDAO.findById(savedAddress.getIdAddress().toString());
        assertTrue(updatedAddress.isPresent());
        assertEquals("Metropolis", updatedAddress.get().getTown());
        assertEquals(65432, updatedAddress.get().getZipcode());
    }
}
