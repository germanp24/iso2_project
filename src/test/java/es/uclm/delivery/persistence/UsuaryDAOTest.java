package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Usuary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuaryDAOTest {

    @Autowired
    private UsuaryDAO usuaryDAO;

    @Test
    void testSaveAndFindById() {
        // Crear y guardar un usuario
        Usuary usuary = new Usuary();
        usuary.setEmail("test@example.com");
        usuary.setPassword("password123");
        usuary.setRole("ROLE_CLIENT");
        Usuary savedUsuary = usuaryDAO.save(usuary);

        // Recuperar por ID
        Optional<Usuary> foundUsuary = usuaryDAO.findById(savedUsuary.getIdUsuary());
        assertTrue(foundUsuary.isPresent());
        assertEquals("test@example.com", foundUsuary.get().getEmail());
    }

    @Test
    void testExistsByEmail() {
        // Crear y guardar un usuario
        Usuary usuary = new Usuary();
        usuary.setEmail("exist@example.com");
        usuary.setPassword("password123");
        usuary.setRole("ROLE_ADMIN");
        usuaryDAO.save(usuary);

        // Verificar existencia por correo
        assertTrue(usuaryDAO.existsByEmail("exist@example.com"));
        assertFalse(usuaryDAO.existsByEmail("nonexistent@example.com"));
    }

    @Test
    void testFindByEmailAndPassword() {
        // Crear y guardar un usuario
        Usuary usuary = new Usuary();
        usuary.setEmail("login@example.com");
        usuary.setPassword("securepassword");
        usuary.setRole("ROLE_ADMIN");
        usuaryDAO.save(usuary);

        // Validar credenciales correctas
        Optional<Usuary> found = usuaryDAO.findByEmailAndPassword("login@example.com", "securepassword");
        assertTrue(found.isPresent());
        assertEquals("login@example.com", found.get().getEmail());

        // Validar credenciales incorrectas
        Optional<Usuary> notFound = usuaryDAO.findByEmailAndPassword("wrong@example.com", "wrongpassword");
        assertFalse(notFound.isPresent());
    }
}
