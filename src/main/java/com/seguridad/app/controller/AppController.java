package com.seguridad.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/panel")
    public String panel(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("roles", auth.getAuthorities());
        return "panel";
    }

    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }

    @GetMapping("/perfil")
    public String perfilUsuario(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        return "perfil";
    }
}