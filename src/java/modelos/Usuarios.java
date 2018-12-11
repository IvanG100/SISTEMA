/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Usuarios {
    private int id_usuario;
    private String login_usuario;
    private String nombre_usuario;
    private String clave_usuario;
    private Roles rol;

    public Usuarios() {
    }

    public Usuarios(int id_usuario, String login_usuario, String nombre_usuario, String clave_usuario, Roles rol) {
        this.id_usuario = id_usuario;
        this.login_usuario = login_usuario;
        this.nombre_usuario = nombre_usuario;
        this.clave_usuario = clave_usuario;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave_usuario() {
        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        this.clave_usuario = clave_usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    

    
    
    
}
