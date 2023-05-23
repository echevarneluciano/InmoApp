package com.example.inmoapp.modelo;

public class Usuario {

    private String Usuario;
    private String Clave;

    public Usuario(){

    }

    public Usuario(String usuario, String clave) {
        this.Usuario = usuario;
        this.Clave = clave;
    }

    public String getUsuario() {
        return Usuario;
    }


    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

}
