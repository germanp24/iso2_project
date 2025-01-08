package es.uclm.delivery.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.RestaurantAdministrator;

@Repository
public interface RestaurantAdministratorDAO extends JpaRepository<RestaurantAdministrator, String> {
    RestaurantAdministrator findByDni (String dni);
}