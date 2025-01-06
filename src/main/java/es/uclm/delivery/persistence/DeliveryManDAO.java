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
    /*
     * Se hereda de JpaRepository operaciones de acceso a datos comunes a todas las
     * entidades:
     * E save(E)
     * List<E> saveAll(List<E>)
     * List<E> findAll()
     * E findById(id)
     * delete(E)
     * deleteById(id)
     * ...
     *
     * Aquí se podrían definir consultas de selección más específicas y complejas
     **/
}