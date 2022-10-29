package co.com.sergio.bkreservaslacasadelturismo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Project bkreservaslacasadelturismo
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 29/10/2022 - 16:17
 **/
@Entity
@Table(name = "cotizacion")
public class Cotizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String para;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private int cant_personas;

    @Column(nullable = false)
    private int valor_total;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCotizacion;

    @OneToOne
    @JoinColumn(name = "solicitud_id")
    private Solicitud solicitud_id;

    @ManyToMany(mappedBy = "cotizacionList")
    private List<Restricciones> restriccionesList;

    @OneToOne(mappedBy = "cotizacion_id", cascade = CascadeType.ALL)
    private Cuestionario cuestionario_id;

    /**
     * Getter y Setter
     **/

    public Solicitud getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(Solicitud solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public LocalDate getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(LocalDate fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public List<Restricciones> getRestriccionesList() {
        return restriccionesList;
    }

    public void setRestriccionesList(List<Restricciones> restriccionesList) {
        this.restriccionesList = restriccionesList;
    }
}
