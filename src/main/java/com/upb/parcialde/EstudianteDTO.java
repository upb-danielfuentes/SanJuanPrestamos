package com.upb.parcialde;

public class EstudianteDTO {

    private String cedula;
    private String nombre;
    private String apellido;
    private String tel;
    private int numsem;
    private float promacum;
    private String serial;

    public EstudianteDTO() {
        cedula = "";
        nombre = "";
        apellido = "";
        tel = "";
        numsem = 0;
        promacum = 0;
        serial = "";
    }

    public EstudianteDTO(String cedula, String nombre, String apellido, String tel, int numsem, float promacum,
                         String serial) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tel = tel;
        this.numsem = numsem;
        this.promacum = promacum;
        this.serial = serial;

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getNumsem() {
        return numsem;
    }

    public void setNumsem(int numsem) {
        this.numsem = numsem;
    }

    public float getPromacum() {
        return promacum;
    }

    public void setPromacum(float promacum) {
        this.promacum = promacum;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
