package es.uclm.delivery.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.DeliveryService;

@Repository
public interface DeliveryServiceDAO extends JpaRepository<DeliveryService, String> {

}