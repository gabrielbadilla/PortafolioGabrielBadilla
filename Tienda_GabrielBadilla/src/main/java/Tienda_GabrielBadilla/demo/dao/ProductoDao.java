/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Tienda_GabrielBadilla.demo.dao;

import Tienda_GabrielBadilla.demo.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gabriel badilla
 */
public interface ProductoDao extends JpaRepository <Producto, Long>{
    
}
