package co.com.sergio.bkreservaslacasadelturismo.repository;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 8:44
 **/

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion,Integer> {
}
