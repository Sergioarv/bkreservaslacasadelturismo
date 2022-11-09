package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 14:48
 **/
public interface FlyerService {

    List<Flyer> getFlyer();

    Flyer createFlyer(Flyer flyerJson, MultipartFile file);

    Flyer editFlyer(Flyer flyerJson, MultipartFile file);

    Boolean deleteFlyer(Flyer flyer);
}
