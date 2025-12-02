package com.seguridad.app.config;

import com.seguridad.app.entity.Usuario;
import com.seguridad.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            
            // ADMIN: pass = "admin123"
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            
            // USER: pass = "user123"
            Usuario user = new Usuario();
            user.setUsername("pepito");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");

            usuarioRepository.save(admin);
            usuarioRepository.save(user);

            System.out.println("--- DATOS DE PRUEBA CREADOS ---");
            System.out.println("Admin: admin / admin123");
            System.out.println("User: pepito / user123");
        }
    }
}