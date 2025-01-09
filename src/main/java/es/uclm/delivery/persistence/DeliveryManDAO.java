package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.business.entity.Usuary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.DeliveryMan;

import java.util.Optional;

@Repository
public interface DeliveryManDAO extends JpaRepository<DeliveryMan, Long> {
    DeliveryMan findByDni (String dni);
    Optional<DeliveryMan> findByUsuary_Email(String email);
    Optional<DeliveryMan> findByUsuary(Usuary usuary);
}