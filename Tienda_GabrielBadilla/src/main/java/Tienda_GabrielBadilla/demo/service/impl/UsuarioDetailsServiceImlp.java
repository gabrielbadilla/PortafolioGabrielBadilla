/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda_GabrielBadilla.demo.service.impl;

import Tienda_GabrielBadilla.demo.dao.UsuarioDao;
import Tienda_GabrielBadilla.demo.domain.Rol;
import Tienda_GabrielBadilla.demo.domain.Usuario;
import Tienda_GabrielBadilla.demo.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gabriel badilla
 */
@Service("userDetailsService")
public class UsuarioDetailsServiceImlp implements UsuarioDetailsService, UserDetailsService{
    @Autowired 
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // busca el usuario por el username en la tabla
        Usuario usuario = usuarioDao.findByUsername(username); 
        //si no eciste el ususairo lanza una exepcion
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        //si esta aca es porque eciste el usuario sacamos los reoles que tiene
        var roles = new ArrayList<GrantedAuthority>();
        for(Rol rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //se devuelve user(clase de userdetails
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
