package co.com.sergio.bkreservaslacasadelturismo.controller;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import co.com.sergio.bkreservaslacasadelturismo.service.FlyerService;
import co.com.sergio.bkreservaslacasadelturismo.utils.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 14:38
 **/

@RestController
@RequestMapping("/flyer")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FlyerController {

    @Autowired
    private FlyerService flyerService;

    /**
     * Método encargado de listar todos los flyer
     * @return lista de flyer
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<Flyer>>> getFlyer(){
        GeneralResponse<List<Flyer>> response = new GeneralResponse<>();
        List<Flyer> data;
        HttpStatus status = HttpStatus.OK;

        data = flyerService.getFlyer();

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if(data.size() > 1){
                response.setMessage("Lista de Flyer obtenida con exito");
            } else if (data.size() == 1) {
                response.setMessage("Flyer obtenido con exito");
            }else {
                response.setSuccess(false);
                response.setMessage("La lista de Flyer se encuentra vacia");
            }
        } else {
            response.setData(null);
            response.setMessage("Hubo un error al obtener la lista de Flyer");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de filtrar los flyer
     * @param nombre atributo del flyer
     * @param descripcion atributo del flyer
     * @param pagina numero de pagina a filtrar
     * @param cantPagina numero de flyer por pagina
     * @return paginacion de flyer filtrados
     */
    @GetMapping("/filter")
    public ResponseEntity<GeneralResponse<Page<Flyer>>> filterFlyer(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "pagina", defaultValue = "0", required = false) int pagina,
            @RequestParam(value = "cantPagina", defaultValue = "10", required = false) int cantPagina
    ) {

        GeneralResponse<Page<Flyer>> response = new GeneralResponse<>();
        Page<Flyer> data;
        HttpStatus status = HttpStatus.OK;

        Pageable pageable = PageRequest.of(pagina, cantPagina, Sort.by("nombre").ascending());

        data = flyerService.filterFlyer(nombre, descripcion, pageable);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if(data.getContent().size() > 1){
                response.setMessage("Lista de Flyer obtenida con exito");
            } else if (data.getContent().size() == 1) {
                response.setMessage("Flyer obtenido con exito");
            }else {
                response.setSuccess(false);
                response.setMessage("La lista de Flyer se encuentra vacia");
            }
        } else {
            response.setData(null);
            response.setMessage("Hubo un error al obtener la lista de Flyer");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de Crear el Flyer
     * @param file Archivo de imagen del Flyer
     * @param flyer Json del Flyer
     * @return Response con el Flyer creado
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<Flyer>> createFlyer(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam("flyer") String flyer) {

        GeneralResponse<Flyer> response = new GeneralResponse<>();
        HttpStatus status = HttpStatus.OK;
        Flyer data;

        Flyer flyerJson;

        try {
            ObjectMapper obj = new ObjectMapper();
            flyerJson = obj.readValue(flyer, Flyer.class);

            if(!verificarImagen(file) && file != null){
                response.setData(flyerJson);
                response.setMessage("Por favor selecciona una imagen en .png o .jpg");
                response.setSuccess(false);

                return new ResponseEntity<>(response, status);
            }

            data = flyerService.createFlyer(flyerJson, file);

            if(data == null){
                response.setData(null);
                response.setMessage("Hubo un error al crear el Flyer");
                response.setSuccess(false);
            } else {
                response.setData(data);
                response.setMessage("El Flyer se creo con exito");
                response.setSuccess(true);
            }

        } catch (JsonProcessingException e) {
            response.setData(null);
            response.setMessage("Hubo un error al crear el Flyer");
            response.setSuccess(false);
        } catch (IOException e) {
            response.setData(null);
            response.setMessage("Hubo un error al crear la imagen del Flyer");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de Editar el Flyer
     * @param file Archivo de imagen del Flyer
     * @param flyer Json del Flyer
     * @return Response con el Flyer Editado
     */
    @PostMapping("/editFlyer")
    public ResponseEntity<GeneralResponse<Flyer>> editFlyer(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam("flyer") String flyer) {

        GeneralResponse<Flyer> response = new GeneralResponse<>();
        HttpStatus status = HttpStatus.OK;
        Flyer data;

        Flyer flyerJson;

        try {
            ObjectMapper obj = new ObjectMapper();
            flyerJson = obj.readValue(flyer, Flyer.class);

            if (!verificarImagen(file) && file != null) {
                response.setData(flyerJson);
                response.setMessage("Por favor selecciona una imagen en .png o .jpg");
                response.setSuccess(false);

                return new ResponseEntity<>(response, status);
            }

            data = flyerService.editFlyer(flyerJson, file);

            if(data == null){
                response.setData(null);
                response.setMessage("Hubo un error al editar la imagen del Flyer");
                response.setSuccess(false);
            }else{
                response.setData(data);
                response.setMessage("El Flyer se edito con exito");
                response.setSuccess(true);
            }
        } catch (JsonProcessingException e) {
            response.setData(null);
            response.setMessage("Hubo un error al editar la imagen del Flyer");
            response.setSuccess(false);
        } catch (IOException e) {
            response.setData(null);
            response.setMessage("Hubo un error al editar el Flyer");
            response.setSuccess(false);
        }
        return null;
    }

    /**
     * Método encargado de eliminar un flyer
     * @param flyer elemento a eliminar
     * @return booleano que confirma su eliminación
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> deleteFlyer(@RequestBody Flyer flyer){

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean data;
        HttpStatus status = HttpStatus.OK;

        data = flyerService.deleteFlyer(flyer);

        if(data){
            response.setData(true);
            response.setMessage("El Flyer se elimino con exito");
            response.setSuccess(true);
        }else{
            response.setData(false);
            response.setMessage("El Flyer no se pudo eliminar");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de verificar el multipart file
     * @param file; Multipart file que llega
     * @return booleano segun la verificación
     * @throws IOException
     */
    private boolean verificarImagen(MultipartFile file) throws IOException {

        if (file != null) {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if (bi == null) {
                return false;
            }
        }
        return true;
    }
}
