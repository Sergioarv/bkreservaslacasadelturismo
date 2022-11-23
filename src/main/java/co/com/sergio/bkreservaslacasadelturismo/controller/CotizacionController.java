package co.com.sergio.bkreservaslacasadelturismo.controller;

import co.com.sergio.bkreservaslacasadelturismo.entity.Cotizacion;
import co.com.sergio.bkreservaslacasadelturismo.service.CotizacionService;
import co.com.sergio.bkreservaslacasadelturismo.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 23/11/2022 - 8:43
 **/

@RestController
@RequestMapping("/cotizacion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    /**
     * Método encargado de obtener todas las cotizaciones
     * @return lista de cotizaciones sin filtros
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<Cotizacion>>> getCotizacion(){

        GeneralResponse<List<Cotizacion>> response = new GeneralResponse();
        List<Cotizacion> data;
        HttpStatus status = HttpStatus.OK;

        data = cotizacionService.getCoticacion();

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if(data.size() > 1){
                response.setMessage("Lista de Cotizaciones obtenida con exito");
            } else if (data.size() == 1) {
                response.setMessage("Cotizacion obtenido con exito");
            }else {
                response.setSuccess(false);
                response.setMessage("La lista de Cotizaciones se encuentra vacia");
            }
        } else {
            response.setData(null);
            response.setMessage("Hubo un error al obtener la lista de Cotizaciones");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de crea una cotizacion
     * @param cotizacion elemento a crear
     * @return elemento creado
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<Cotizacion>> createCotizacion(@RequestBody Cotizacion cotizacion){
        GeneralResponse<Cotizacion> response = new GeneralResponse<>();
        Cotizacion data;
        HttpStatus status = HttpStatus.OK;

        data = cotizacionService.createCotizacion(cotizacion);

        if(data != null){
            response.setData(data);
            response.setMessage("La cotizacion se crea con exito");
            response.setSuccess(true);
        }else {
            response.setData(null);
            response.setMessage("Hubo un error al crear la cotizacion");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de editar una cotizacion
     * @param cotizacion elemento a editar
     * @return elemento editado
     */
    @PutMapping
    public ResponseEntity<GeneralResponse<Cotizacion>> editCotizacion(@RequestBody Cotizacion cotizacion){
        GeneralResponse<Cotizacion> response = new GeneralResponse<>();
        Cotizacion data;
        HttpStatus status = HttpStatus.OK;

        data = cotizacionService.editCotizacion(cotizacion);

        if(data != null){
            response.setData(data);
            response.setMessage("La cotizacion se edito con exito");
            response.setSuccess(true);
        }else {
            response.setData(null);
            response.setMessage("Hubo un error al editar la cotizacion");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encardado de eliminar una cotizacion
     *
     * @param cotizacion elemento a liminar
     * @return retorna un boolenano confirmando la eliminacion
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> deleteCotizacion(@RequestBody Cotizacion cotizacion){

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        boolean data;
        HttpStatus status = HttpStatus.OK;

        try{
            data = cotizacionService.deleteCotizacion(cotizacion);

            if (data) {
                response.setData(true);
                response.setSuccess(true);
                response.setMessage("Cotizacion eliminado con exito");
            } else {
                response.setData(false);
                response.setSuccess(false);
                response.setMessage("No se pudo eliminar la cotizacion");
            }
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(true);
            response.setMessage("No se puede eliminar la cotizacion");
        }
        return new ResponseEntity<>(response, status);
    }
}
