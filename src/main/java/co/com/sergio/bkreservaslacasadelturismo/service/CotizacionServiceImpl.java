package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;
import co.com.sergio.bkreservaslacasadelturismo.repository.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 8:46
 **/

@Service
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Cotizacion> getCoticacion() {
        return cotizacionRepository.findAll(Sort.by("fechaCotizacion").descending());
    }

    @Override
    @Transactional
    public Cotizacion createCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    @Override
    @Transactional
    public Cotizacion editCotizacion(Cotizacion cotizacion) {

        Optional<Cotizacion> result = cotizacionRepository.findById(cotizacion.getId());

        if(result.isPresent()){
            return  cotizacionRepository.save(cotizacion);
        }

        return null;
    }

    @Override
    @Transactional
    public boolean deleteCotizacion(Cotizacion cotizacion) {
        cotizacionRepository.delete(cotizacion);
        return true;
    }
}
