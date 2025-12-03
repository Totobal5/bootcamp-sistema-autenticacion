# **Sistema de Autenticación y Autorización**

Este proyecto implementa un sistema de seguridad completo utilizando **Spring Security 6** y **Spring Boot 3**. Su objetivo principal es demostrar la configuración de una arquitectura de seguridad robusta, manejando autenticación personalizada contra base de datos y autorización basada en roles (RBAC).

## **Stack Tecnológico**

* **Core:** Java 17, Spring Boot 3\.  
* **Seguridad:** Spring Security 6, BCrypt.  
* **Persistencia:** Spring Data JPA, H2 Database (En memoria).  
* **Frontend:** Thymeleaf con integración de *Thymeleaf Extras Spring Security*.  
* **Build:** Maven.

## **Funcionalidades Clave**

### **Autenticación Personalizada (UserDetailsService)**

En lugar de usar la configuración por defecto en memoria, se implementó un servicio UserDetailsServiceImpl que conecta con la base de datos (JPA) para validar credenciales. Las contraseñas se almacenan encriptadas utilizando **BCrypt**.

### **Control de Acceso por Roles (RBAC)**

La aplicación segrega el acceso a las rutas según el rol del usuario autenticado:

* **Público:** Login, recursos estáticos.  
* **Usuario (USER):** Acceso al Panel General y Perfil (/panel, /perfil).  
* **Administrador (ADMIN):** Acceso total, incluyendo la zona de gestión (/admin/\*\*).

### **Seguridad en Vistas**

Se utiliza el motor de plantillas Thymeleaf para renderizar contenido condicionalmente. Por ejemplo, el botón "Zona Admin" solo es visible si el usuario logueado posee el rol ROLE\_ADMIN.

## **Despliegue y Pruebas**

### **Prerrequisitos**

* JDK 17 instalado.  
* Maven.

### **Ejecución**

1. Clonar el repositorio:  
   git clone \[https://github.com/Totobal5/bootcamp-sistema-autenticacion.git\](https://github.com/Totobal5/bootcamp-sistema-autenticacion.git)

2. Iniciar la aplicación:  
   mvn spring-boot:run

3. Acceder al login: http://localhost:8080/login

## **Credenciales de Acceso**

El sistema cuenta con un DataInitializer que precarga usuarios de prueba al arrancar la aplicación (H2 Database se reinicia en cada ejecución).

| Rol | Usuario | Contraseña | Permisos |
| :---- | :---- | :---- | :---- |
| **Administrador** | admin | admin123 | Acceso total y panel administrativo. |
| **Usuario** | pepito | user123 | Acceso a panel general y perfil. |

## **Estructura de Seguridad**

La configuración principal reside en SecurityConfig.java, donde se define la cadena de filtros (SecurityFilterChain):

http.authorizeHttpRequests(auth \-\> auth  
    .requestMatchers("/admin/\*\*").hasRole("ADMIN")  
    .requestMatchers("/panel/\*\*").authenticated()  
    .anyRequest().authenticated()  
)  
.formLogin(login \-\> login  
    .loginPage("/login") // Vista personalizada  
    .defaultSuccessUrl("/panel", true)  
    .permitAll()  
);