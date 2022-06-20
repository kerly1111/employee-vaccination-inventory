# employee-vaccination-inventory
Aplicación para llevar el control del inventario del estado de vacunación de los empleados.

## Instalación

Debes tener instalado jdk-17.0.3.1 previamente a la clonación del proyecto.

Luego de haber clonado el projecto ejecutar el comando gradle para descargar todas las depencias del proyecto.
```bash
gradle build --refresh-dependencies
```


## Autenticación Básica

El api cuenta con un sistema de seguridad de autenticación básica basado en un usuario y contraseña que se insertan automaticamente en la base de datos cuand se inicia la Api.
```bash
Usuario: admin
Passwor: 12345
```
