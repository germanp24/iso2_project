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

        Address address = new Address("Main Street", "123", "Springfield", 12345);

        Address savedAddress = addressDAO.save(address);

        assertNotNull(savedAddress.getIdAddress());
        assertEquals("Main Street", savedAddress.getStreet());
        assertEquals("123", savedAddress.getNumber());
        assertEquals("Springfield", savedAddress.getTown());
        assertEquals(12345, savedAddress.getZipcode());

        Optional<Address> foundAddress = addressDAO.findById(savedAddress.getIdAddress().toString());
        assertTrue(foundAddress.isPresent());
        assertEquals("Main Street", foundAddress.get().getStreet());
    }

    @Test
    void testDeleteAddress() {
        Address address = new Address("Elm Street", "456", "Shelbyville", 54321);
        Address savedAddress = addressDAO.save(address);

        addressDAO.deleteById(savedAddress.getIdAddress().toString());

        Optional<Address> foundAddress = addressDAO.findById(savedAddress.getIdAddress().toString());
        assertFalse(foundAddress.isPresent());
    }

    @Test
    void testFindAllAddresses() {
        Address addr1 = new Address("Baker Street", "221B", "London", 56789);
        Address addr2 = new Address("Fifth Avenue", "789", "New York", 67890);
        addressDAO.save(addr1);
        addressDAO.save(addr2);

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
