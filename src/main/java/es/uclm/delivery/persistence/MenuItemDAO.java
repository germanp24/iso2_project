package es.uclm.delivery.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.MenuItem;

import java.util.List;

@Repository
public interface MenuItemDAO extends JpaRepository<MenuItem, String> {
    List<MenuItem> findByRestaurantCif(String cif);
}