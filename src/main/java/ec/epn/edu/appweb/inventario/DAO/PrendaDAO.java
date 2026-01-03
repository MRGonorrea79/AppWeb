package ec.epn.edu.appweb.inventario.DAO;

import ec.epn.edu.appweb.inventario.modelo.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaDAO extends JpaRepository<Prenda, Long> {

    default void eliminarPrenda(Prenda prenda) {
        delete(prenda);
    }


}
