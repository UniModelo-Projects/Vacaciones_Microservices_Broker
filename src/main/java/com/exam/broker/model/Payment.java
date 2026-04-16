package com.exam.broker.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payment {
    private String id;
    
    @JsonProperty("ordenId")
    @JsonAlias({"orderId", "ordenId"})
    private String ordenId;
    
    @JsonProperty("monto")
    @JsonAlias({"amount", "monto"})
    private Double monto;
    
    @JsonProperty("estado")
    @JsonAlias({"status", "estado"})
    private String estado;

    public Payment() {}

    public Payment(String id, String ordenId, Double monto, String estado) {
        this.id = id;
        this.ordenId = ordenId;
        this.monto = monto;
        this.estado = estado;
    }

    // Explicitly added getters/setters just in case
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrdenId() { return ordenId; }
    public void setOrdenId(String ordenId) { this.ordenId = ordenId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
