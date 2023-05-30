package com.example.inmoapp.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pago implements Serializable {

    private int id;
    private int mes;
    private Contrato contrato;
    private String fechaPagado;
    private int importe;

    public Pago() {}

    public Pago(int id, int mes, Contrato contrato, String fechaPagado, int importe) {
        this.id = id;
        this.mes = mes;
        this.contrato = contrato;
        this.fechaPagado = fechaPagado;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(String fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
