package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;

import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 8:46
 **/
public interface CotizacionService {
    List<Cotizacion> getCoticacion();

    Cotizacion createCotizacion(Cotizacion cotizacion);

    Cotizacion editCotizacion(Cotizacion cotizacion);

    boolean deleteCotizacion(Cotizacion cotizacion);
}
