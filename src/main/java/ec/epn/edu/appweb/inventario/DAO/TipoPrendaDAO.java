package ec.epn.edu.appweb.inventario.DAO;

import ec.epn.edu.appweb.inventario.modelo.TipoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPrendaDAO extends JpaRepository<TipoPrenda, Long> {

}
