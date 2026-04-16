package com.exam.broker.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {
    private String id;
    
    @JsonProperty("nombre")
    @JsonAlias({"name", "nombre"})
    private String nombre;

    @JsonProperty("descripcion")
    @JsonAlias({"description", "descripcion"})
    private String descripcion;

    @JsonProperty("precio")
    @JsonAlias({"price", "precio"})
    private Double precio;

    @JsonProperty("stock")
    private Integer stock;

    public Product() {}

    public Product(String id, String nombre, String descripcion, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Explicitly added getters/setters just in case Lombok isn't fully set up in this module
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
