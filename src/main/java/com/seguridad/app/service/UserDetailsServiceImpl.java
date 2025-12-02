package com.seguridad.app.service;

import com.seguridad.app.entity.Usuario;
import com.seguridad.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Esta clase es el puente entre Spring Security y nuestra Base de Datos.
 * Spring Security llama a loadUserByUsername cuando alguien intenta loguearse.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en nuestra DB
        Usuario appUser = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertimos nuestro 'Usuario' (entidad) a un 'UserDetails' (objeto de Spring Security)
        // El método .roles() automáticamente agrega el prefijo "ROLE_"
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole())        
                .build();
    }
}