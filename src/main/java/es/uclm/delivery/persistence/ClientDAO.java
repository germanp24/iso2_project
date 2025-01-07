package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Usuary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDAO extends JpaRepository<Client, Long> {
    Client findByDni(String dni);
    Optional<Client> findByUsuary(Usuary usuary);
    Optional<Client> findByUsuary_Email(String email);

}
