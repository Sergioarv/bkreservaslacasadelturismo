package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 9:36
 **/

public interface SolicitudService {
    List<Solicitud> getSolicitud();

    Page<Solicitud> filterSolicitud(String nombre, String apellido, String email, String estado, Date fecha_solicitud, Pageable pageable);

    Solicitud createSolicitud(Solicitud solicitud);

    Solicitud editSolicitud(Solicitud solicitud);

    boolean deleteCotizacion(Solicitud solicitud);
}
