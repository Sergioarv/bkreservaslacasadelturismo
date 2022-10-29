package co.com.sergio.bkreservaslacasadelturismo.service;

import co.com.sergio.bkreservaslacasadelturismo.entity.Flyer;
import co.com.sergio.bkreservaslacasadelturismo.repository.FlyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Flyer> getFlyer() {
        return flyerRepository.findAll();
    }
}
