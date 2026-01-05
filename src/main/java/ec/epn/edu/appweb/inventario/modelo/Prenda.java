package ec.epn.edu.appweb.inventario.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "prendas")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String talla;
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoPrenda tipoPrenda;

    private Double precio;
    private Integer stock;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public TipoPrenda getTipoPrenda() { return tipoPrenda; }
    public void setTipoPrenda(TipoPrenda tipoPrenda) { this.tipoPrenda = tipoPrenda; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getImagenUrl() {
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
