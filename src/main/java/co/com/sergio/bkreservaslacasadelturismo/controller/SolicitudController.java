package co.com.sergio.bkreservaslacasadelturismo.controller;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;
import co.com.sergio.bkreservaslacasadelturismo.entity.Solicitud;
import co.com.sergio.bkreservaslacasadelturismo.service.SolicitudService;
import co.com.sergio.bkreservaslacasadelturismo.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 9:34
 **/
@RestController
@RequestMapping("/solicitud")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    /**
     * Método encargado de obtener todas las solicitudes
     * @return lista de solicitudes sin filtro
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<Solicitud>>> getSolicitud() {

        GeneralResponse<List<Solicitud>> response = new GeneralResponse<>();
        List<Solicitud> data;
        HttpStatus status = HttpStatus.OK;

        data = solicitudService.getSolicitud();

        if (data != null) {
            response.setData(data);
            response.setMessage("La solicitud se creo con exito");
            response.setSuccess(true);
        } else {
            response.setData(null);
            response.setMessage("Hubo un error al crear la solicitud");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de filtrar las solicitudes
     * @param nombre
     * @param apellido
     * @param email
     * @param estado
     * @param fecha_solicitud
     * @param pagina
     * @param catnPagina
     * @return retorna la paginacion de los elementos filtrados
     */
    @GetMapping("/filter")
    public ResponseEntity<GeneralResponse<Page<Solicitud>>> filterSolicitud(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellido", required = false) String apellido,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "fecha_solicitud", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_solicitud,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int catnPagina
    ) {

        GeneralResponse<Page<Solicitud>> response = new GeneralResponse<>();
        Page<Solicitud> data;
        HttpStatus status = HttpStatus.OK;

        try {

            Pageable pageable = PageRequest.of(pagina, catnPagina, Sort.by("fecha_solicitud").descending());

            data = solicitudService.filterSolicitud(nombre, apellido, email, estado, fecha_solicitud, pageable);

            if (data != null) {

                response.setData(data);
                response.setSuccess(true);

                if (data.getContent().size() > 1) {
                    response.setMessage("Lista de solicitudes obtenida con exito");
                } else if (data.getContent().size() == 1) {
                    response.setMessage("Solicitud obtenido con exito");
                } else {
                    response.setSuccess(false);
                    response.setMessage("No se encontro ninguna solicitud");
                }
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Lista de solicitudes esta vacia");
            }
        } catch (Exception e) {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al obtener la lista de solicitudes");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crear una nueva solicitud
     * @param solicitud elemento a crear
     * @return elemento creado
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<Solicitud>> createSolicitud(@RequestBody Solicitud solicitud){

        GeneralResponse<Solicitud> response = new GeneralResponse<>();
        Solicitud data;
        HttpStatus status = HttpStatus.OK;

        data = solicitudService.createSolicitud(solicitud);

        if(data != null){
            response.setData(data);
            response.setMessage("La solicitud se creo correctamente");
            response.setSuccess(true);
        }else {
            response.setData(null);
            response.setMessage("Hubo un error al crear la solicitud");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de editar una solicitud
     * @param solicitud elemento a editar
     * @return elemento editado
     */
    @PutMapping
    public ResponseEntity<GeneralResponse<Solicitud>> editSolicitud(@RequestBody Solicitud solicitud){

        GeneralResponse<Solicitud> response = new GeneralResponse<>();
        Solicitud data;
        HttpStatus status = HttpStatus.OK;

        data = solicitudService.editSolicitud(solicitud);

        if(data != null){
            response.setData(data);
            response.setMessage("La solicitud se edito correctamente");
            response.setSuccess(true);
        }else {
            response.setData(null);
            response.setMessage("Hubo un error al editar la solicitud");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de eliminar una solicitud
     * @param solicitud elemento a liminar
     * @return boolenano confirmanco la eliminacion
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> deleteSolicitud(@RequestBody Solicitud solicitud){

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        boolean data;
        HttpStatus status = HttpStatus.OK;

        try{
            data = solicitudService.deleteCotizacion(solicitud);

            if (data) {
                response.setData(true);
                response.setSuccess(true);
                response.setMessage("Solicitud eliminado con exito");
            } else {
                response.setData(false);
                response.setSuccess(false);
                response.setMessage("No se pudo eliminar la solicitud");
            }
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(true);
            response.setMessage("No se puede eliminar la solicitud");
        }
        return new ResponseEntity<>(response, status);
    }
}
