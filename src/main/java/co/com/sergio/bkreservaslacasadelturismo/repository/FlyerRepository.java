package co.com.sergio.bkreservaslacasadelturismo.repository;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 14:47
 **/

@Repository
public interface FlyerRepository extends JpaRepository<Flyer, Integer> {

    @Query(value = "select * from flyer as f where lower(f.nombre) like lower(concat('%',:nombre,'%')) and lower(f.descripcion) like lower(concat('%',:descripcion,'%'))", nativeQuery = true)
    Page<Flyer> filterAll(String nombre, String descripcion, Pageable pageable);

    @Query(value = "select * from flyer as f where lower(f.nombre) like lower(concat('%',:nombre,'%'))", nativeQuery = true)
    Page<Flyer> filterNombre(String nombre, Pageable pageable);

    @Query(value = "select * from flyer as f where lower(f.descripcion) like lower(concat('%',:descripcion,'%'))", nativeQuery = true)
    Page<Flyer> filterDescripcion(String descripcion, Pageable pageable);
}
