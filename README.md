# Prueba Técnica Backend – Comercial Card (PruebaPTM)

Este proyecto es una API REST desarrollada en SpringBoot, con conexión a MySQL y pruebas unitarias integradas, que permite realizar operaciones CRUD sobre productos, calcular el valor total del inventario y generar combinaciones de productos por precio. Hace parte de la prueba técnica para el cargo Analista Desarrollador JAVA.

---

## ¿Cómo ejecutar esta aplicación localmente?

### Requisitos previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:

- Java 17 o superior
- Maven
- MySQL (se recomienda usar XAMPP u otro gestor local)
- Puerto MySQL configurado en `3308`
- Un IDE como IntelliJ IDEA, Eclipse o Spring Tool Suite

---

### Clonar, configurar y ejecutar

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/EdwinGordillo/pruebaPTM-back.git
cd pruebaPTM-back
```

2. Cuando el proyecto lo tengas abierto con un IDE como IntelliJ IDEA, Eclipse, Spring Tool Suite o Visual Studio Code, ejecuta esos comandos.

```bash
mvn clean install
mvn spring-boot:run
```

3. El servidor quedara subido en está ruta, descarga el postman y empieza a probar.

```bash
http://localhost:8080
```

### Creación de BD Crea la base de datos y tabla en tu MySQL

1. Revisa el archivo scriptBD.sql y ahí estara todo lo necesario.

### Verificar la configuración del src/main/resources/application.yml

1. Verificar que la conexión este de la siguiente manera.

```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3308/crud_app
    username: root
    password:
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```