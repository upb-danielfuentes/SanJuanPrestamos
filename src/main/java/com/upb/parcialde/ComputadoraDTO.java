package com.upb.parcialde;

public class ComputadoraDTO {

    private String serial;
    private String marca;
    private float tamano;
    private float precio;
    private String sis;
    private String procesador;

    public ComputadoraDTO() {
        serial = "";
        marca = "";
        tamano = 0;
        precio = 0;
        sis = "";
        procesador = "";
    }

    public ComputadoraDTO(String serial, String marca, float tamano, float precio, String sis, String procesador) {
        this.serial = serial;
        this.marca = marca;
        this.tamano = tamano;
        this.precio = precio;
        this.sis = sis;
        this.procesador = procesador;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getSis() {
        return sis;
    }

    public void setSis(String sis) {
        this.sis = sis;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

}
