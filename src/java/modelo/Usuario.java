/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Usuario {
    int id;
    String nombre;
    String correo;
    String contrasenia;
    String rol;
    public Usuario(int id,String nombre,String correo, String contrasenia, String rol){
        this.id=id;
        this.nombre=nombre;
        this.correo=correo;
        this.contrasenia=contrasenia;
        this.rol=rol;
    }
    public Usuario(){
    }
    public int get_id(){
        return this.id;
    }
    public String get_nombre(){
        return this.nombre;
    }
    public String get_correo(){
        return this.correo;
    }
    public String get_contrasenia(){
        return this.contrasenia;
    }
    public String get_rol(){
        return this.rol;
    }
    public void set_id(int id){
        this.id=id;
    }
    public void set_nombre(String nombre){
        this.nombre=nombre;
    }
    public void set_correo(String correo){
        this.correo=correo;
    }
    public void set_contrasenia(String contrasenia){
        this.contrasenia= contrasenia;
    }
    public void set_rol(String rol){
        this.rol=rol;
    }
}
