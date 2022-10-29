package co.com.sergio.bkreservaslacasadelturismo.controller;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import co.com.sergio.bkreservaslacasadelturismo.service.FlyerService;
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
 * @Date 29/10/2022 - 14:38
 **/

@RestController
@RequestMapping("/flyer")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FlyerController {

    @Autowired
    private FlyerService flyerService;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<Flyer>>> getFlyer(){

        GeneralResponse<List<Flyer>> response = new GeneralResponse<>();
        List<Flyer> data;
        HttpStatus status = HttpStatus.OK;

        data = flyerService.getFlyer();

        if(data != null){
            response.setData(data);
            response.setMessage("Flyer obtenidos con exito");
            response.setSuccess(true);
        }else{
            response.setData(null);
            response.setMessage("Error al obtener los Flyer");
            response.setSuccess(false);
        }

        return new ResponseEntity<>(response, status);
    }
}
