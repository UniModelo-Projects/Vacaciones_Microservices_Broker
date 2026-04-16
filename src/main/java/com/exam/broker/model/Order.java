package com.exam.broker.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String id;
    
    @JsonProperty("usuarioId")
    @JsonAlias({"userId", "usuarioId"})
    private String usuarioId;
    
    @JsonProperty("productoIds")
    @JsonAlias({"productIds", "productoIds"})
    private List<String> productoIds;
    
    @JsonProperty("total")
    private Double total;
    
    @JsonProperty("estado")
    @JsonAlias({"status", "estado"})
    private String estado;

    public Order() {}

    public Order(String id, String usuarioId, List<String> productoIds, Double total, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.productoIds = productoIds;
        this.total = total;
        this.estado = estado;
    }

    // Explicitly added getters/setters just in case
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public List<String> getProductoIds() { return productoIds; }
    public void setProductoIds(List<String> productoIds) { this.productoIds = productoIds; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
