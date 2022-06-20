# employee-vaccination-inventory
Aplicación para llevar el control del inventario del estado de vacunación de los empleados.

## Instalación

Debes tener instalado jdk-17.0.3.1 previamente a la clonación del proyecto.

A continuación se detalla el comando de clonación del reporsitorio con el proyecto
```bash
git@github.com:kerly1111/employee-vaccination-inventory.git
```
Luego de haber clonado el projecto ejecutar el comando gradle para descargar todas las depencias del proyecto.
```bash
gradle build --refresh-dependencies
```

## Importante

Cuando se inicia el api automaticamente se crea la base de datos y se insertan los datos de los roles y el usuario administrador esto debido a que el archiv *application.properties* tiene la siguiente configuración.
```bash
spring.jpa.hibernate.ddl-auto=create
```

## Autenticación Básica

El api cuenta con un sistema de seguridad de autenticación básica basado en un usuario y contraseña que se insertan automaticamente en la base de datos cuand se inicia la Api.
```bash
Usuario: admin
Passwor: 12345
```
Cuando se crea un empleado el sistema automaticamente crea un usuario para el mismo, en donde el usuario y la password son la identificaicon del empleado.
## Swagger-OpenAPI

La api cuenta con documentación Swagger-OpenAPI, para accerder luego de iniciado el servidor se debe ingresar a la siguiente URL
```bash
http://localhost:8080/swagger-ui/index.html
```
Aqui se deben colocar las credeciales previamente para consumir los servicios.