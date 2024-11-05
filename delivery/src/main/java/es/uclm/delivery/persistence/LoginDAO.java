package es.uclm.delivery.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.uclm.delivery.business.entity.Login;

@Repository
public interface LoginDAO extends JpaRepository<Login, Long> {
    /*
    * Se hereda de JpaRepository operaciones de acceso a datos comunes a todas las
    entidades:
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

    Login findByEmail(String email);
}