package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;
import co.com.sergio.bkreservaslacasadelturismo.entity.Solicitud;
import co.com.sergio.bkreservaslacasadelturismo.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 9:36
 **/

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Solicitud> getSolicitud() {
        return solicitudRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Solicitud> filterSolicitud(String nombre, String apellido, String email, String estado, Date fecha_solicitud, Pageable pageable) {

        Page<Solicitud> listResult;

        if(nombre != null && apellido != null){
            listResult = solicitudRepository.filterByNombreAndApellido(nombre, apellido, pageable);
        }else if(nombre != null){
            listResult = solicitudRepository.filterByNombre(nombre, pageable);
        } else if (apellido != null) {
            listResult = solicitudRepository.filterByApellido(apellido, pageable);
        } else if (email != null) {
            listResult = solicitudRepository.filterByEmail(email, pageable);
        } else if (fecha_solicitud != null) {
            listResult = solicitudRepository.filterByfecha_solicitud(fecha_solicitud, pageable);
        }else {
            listResult = solicitudRepository.findAll(pageable);
        }

        return listResult;
    }

    @Override
    @Transactional
    public Solicitud createSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    @Transactional
    public Solicitud editSolicitud(Solicitud solicitud) {

        Optional<Solicitud> result = solicitudRepository.findById(solicitud.getId());

        if(result.isPresent()){
            return  solicitudRepository.save(solicitud);
        }

        return null;
    }

    @Override
    @Transactional
    public boolean deleteCotizacion(Solicitud solicitud) {
        solicitudRepository.delete(solicitud);
        return true;
    }
}
