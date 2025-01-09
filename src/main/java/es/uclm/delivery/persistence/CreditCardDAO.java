package es.uclm.delivery.persistence;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.CreditCard;

@Repository
public interface CreditCardDAO extends JpaRepository<CreditCard, UUID> {

}