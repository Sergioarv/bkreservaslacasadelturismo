package co.com.sergio.bkreservaslacasadelturismo.repository;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 14:47
 **/

@Repository
public interface FlyerRepository extends JpaRepository<Flyer, Integer> {
}
