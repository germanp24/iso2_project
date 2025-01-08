package es.uclm.delivery.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.Restaurant;

import java.util.List;

@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant, String> {
    Restaurant findByCif(String cif);
    List<Restaurant> findByLocality(String locality);
    List<Restaurant> findByName(String name);
}