package co.com.sergio.bkreservaslacasadelturismo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 16:34
 **/
@Entity
@Table(name = "restricciones")
public class Restricciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String restriccion;

    @Column(nullable = false)
    private int tipo;

    @JoinTable(name = "cotizacion_restriccion",
    joinColumns = @JoinColumn(name = "id_cotizacion", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_restriccion", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cotizacion> cotizacionList;

    public void agregarCotizacion(Cotizacion cotizacion){
        if( this.cotizacionList == null){
            this.cotizacionList = new ArrayList<>();
        }
        this.cotizacionList.add(cotizacion);
    }

    public void removerCotizacion(Cotizacion cotizacion){
        this.cotizacionList.remove(cotizacion);
        cotizacion.getRestriccionesList().remove(this);
    }

    /**
     * Getter y Setter
     **/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
