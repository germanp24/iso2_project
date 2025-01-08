package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDAO extends JpaRepository<Client, Long> {
    Optional<Client> findByUsuary_Email(String email);

}
