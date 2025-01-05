package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
    Client findByDni(String dni);
}