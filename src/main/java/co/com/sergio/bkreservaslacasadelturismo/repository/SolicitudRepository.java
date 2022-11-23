package co.com.sergio.bkreservaslacasadelturismo.repository;

import co.com.sergio.bkreservaslacasadelturismo.entity.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 9:35
 **/

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    @Query(value = "select * from solicitud as s where lower(s.nombre) like lower(concat('%',:nombre,'%')) and lower(s.apellido) like lower(concat('%',:apellido,'%'))", nativeQuery = true)
    Page<Solicitud> filterByNombreAndApellido(String nombre, String apellido, Pageable pageable);

    @Query(value = "select * from solicitud as s where lower(s.nombre) like lower(concat('%',:nombre,'%'))", nativeQuery = true)
    Page<Solicitud> filterByNombre(String nombre, Pageable pageable);

    @Query(value = "select * from solicitud as s where lower(s.apellido) like lower(concat('%',:apellido,'%'))", nativeQuery = true)
    Page<Solicitud> filterByApellido(String apellido, Pageable pageable);

    @Query(value = "select * from solicitud as s where lower(s.email) like lower(concat('%',:email,'%'))", nativeQuery = true)
    Page<Solicitud> filterByEmail(String email, Pageable pageable);

    @Query(value = "select * from solicitud as s where s.fecha_solicitud = :fecha_solicitud order by s.fecha_solicitud desc", nativeQuery = true)
    Page<Solicitud> filterByfecha_solicitud(Date fecha_solicitud, Pageable pageable);
}
