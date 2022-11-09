package co.com.sergio.bkreservaslacasadelturismo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/11/2022 - 14:43
 **/
public interface CloudinaryService {

    Map cargarImagen(MultipartFile imagen) throws IOException;

    Map eliminarImagen(String idImagen) throws IOException;

}
