package co.com.sergio.bkreservaslacasadelturismo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/11/2022 - 14:45
 **/

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    Cloudinary cloudinary;

    private final Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryServiceImpl() {
        valuesMap.put("cloud_name", "dy5dckhpc");
        valuesMap.put("api_key", "154869343865556");
        valuesMap.put("api_secret", "ImotKIV6RfOeNu8_Odc0sJq04W0");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Map cargarImagen(MultipartFile imagen) throws IOException {
        File file = convertir(imagen);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();

        return result;
    }

    @Override
    public Map eliminarImagen(String idImagen) throws IOException {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    private File convertir(MultipartFile imagen) throws IOException {
        File file = new File(imagen.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();

        return file;
    }
}
