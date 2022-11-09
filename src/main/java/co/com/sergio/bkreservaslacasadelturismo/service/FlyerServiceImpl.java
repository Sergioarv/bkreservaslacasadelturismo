package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import co.com.sergio.bkreservaslacasadelturismo.repository.FlyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 14:49
 **/

@Service
public class FlyerServiceImpl implements FlyerService {

    @Autowired
    private FlyerRepository flyerRepository;

    @Autowired
    private CloudinaryService cloudinaryService;


    @Override
    public List<Flyer> getFlyer() {
        return flyerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Flyer> filterFlyer(String nombre, String descripcion, Pageable pageable) {

        Page<Flyer> listResult;

        if(nombre != null && descripcion != null){
            listResult = flyerRepository.filterAll(nombre, descripcion, pageable);
        }else if (nombre != null){
            listResult = flyerRepository.filterNombre(nombre, pageable);
        } else if (descripcion != null) {
            listResult = flyerRepository.filterDescripcion(descripcion, pageable);
        }else {
            listResult = flyerRepository.findAll(pageable);
        }

        return listResult;
    }

    @Override
    @Transactional
    public Flyer createFlyer(Flyer flyerJson, MultipartFile file) {

        Flyer flyerGuardado;
        flyerGuardado = flyerRepository.save(flyerJson);

        if (flyerGuardado != null) {
            if (file != null) {
                try {
                    Map imagen = cloudinaryService.cargarImagen(file);
                    flyerGuardado.setId_img((String) imagen.get("public_id"));
                    flyerGuardado.setNombre_img((String) imagen.get("original_filename"));
                    String urlImagen = (String) imagen.get("url");
                    flyerGuardado.setUrlImg(urlImagen.replace("http:", "https:"));
                } catch (IOException e) {
                    flyerGuardado = null;
                }
            }
        }

        return flyerGuardado;
    }

    @Override
    @Transactional
    public Flyer editFlyer(Flyer flyerJson, MultipartFile file) {

        Flyer flyerGuardado;
        Flyer flyerEditado = null;

        flyerGuardado = flyerRepository.findById(flyerJson.getId()).orElse(null);

        if (flyerGuardado != null) {
            flyerEditado = flyerRepository.save(flyerJson);

            if (flyerEditado != null) {
                try {
                    if (!flyerEditado.getUrlImg().equals("") && !flyerEditado.getId_img().equals("")) {
                        if(!flyerEditado.getUrlImg().equalsIgnoreCase(flyerGuardado.getId_img())){
                            Map borrado = cloudinaryService.eliminarImagen(flyerEditado.getId_img());
                            if(!borrado.get("result").toString().equalsIgnoreCase("not found")){
                                Map imagen = cloudinaryService.cargarImagen(file);
                                flyerEditado.setId_img((String) imagen.get("public_id"));
                                flyerEditado.setUrlImg((String) imagen.get("url"));
                                flyerEditado.setNombre_img((String) imagen.get("original_filename"));
                            }
                        }
                    }else{
                        flyerEditado.setUrlImg(flyerGuardado.getUrlImg().replace("http:", "https:"));
                    }
                    flyerRepository.save(flyerEditado);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return flyerEditado;
    }

    @Override
    @Transactional
    public Boolean deleteFlyer(Flyer flyer) {

        Flyer flyerOriginal = flyerRepository.findById(flyer.getId()).orElse(null);

        if(flyerOriginal != null){
            try {
                Map borrado = cloudinaryService.eliminarImagen(flyerOriginal.getId_img());
                if (borrado.get("result").toString().equalsIgnoreCase("not found")) {
                    return false;
                }

                flyerRepository.delete(flyerOriginal);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }
}
