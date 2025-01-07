package es.uclm.delivery.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.Usuary;

@Repository
public interface UsuaryDAO extends JpaRepository<Usuary, Long> {
    Optional<Usuary> findByEmailAndPassword(String email, String password);
    Optional<Usuary> existsByEmail(String email);
    Optional<Usuary> findByEmail(String email);

}
