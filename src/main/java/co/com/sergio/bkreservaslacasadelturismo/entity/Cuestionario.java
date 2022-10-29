package co.com.sergio.bkreservaslacasadelturismo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 17:08
 **/

@Entity
@Table(name = "cuestionario")
public class Cuestionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private HashMap<String, String> respuestas;

    @OneToOne
    @JoinColumn(name = "cotizacion_id")
    private Cotizacion cotizacion_id;

    /**
     * Getter y Setter
     **/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(HashMap<String, String> respuestas) {
        this.respuestas = respuestas;
    }

    public Cotizacion getCotizacion_id() {
        return cotizacion_id;
    }

    public void setCotizacion_id(Cotizacion cotizacion_id) {
        this.cotizacion_id = cotizacion_id;
    }
}
