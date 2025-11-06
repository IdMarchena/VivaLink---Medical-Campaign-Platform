/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author Usuario
 */
public interface RolDao {
    int buscarIdPorNombreRol(String nombre);
    List<String> roles();
    String buscarNombreRolPorIdRol(int id);
    
    
}
