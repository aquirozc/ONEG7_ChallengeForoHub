# Alura Challenge | ForoHub

Foro Hub es una API RESTful desarrollada para gestionar un foro en línea, donde los usuarios pueden interactuar mediante la creación, edición, eliminación y visualización de tópicos. 

## Caracteristicas 

- Autenticación vía JWT mediante el uso de la libreria OAuth2ResourceFilter
- Emisión de tokens firmados por una llave simétrica RSA2048
- Uso de OncePerRequestFilters para evitar la autenticación con tokens revocados

## Endpoints disponibles

### Autenticación 
| Método | Endpoint         | Descripción                         | Autenticación |
|--------|------------------|------------------------------------ |---------------|
| POST   | `/auth/register` | Registro de un nuevo usuario        | ❌            |
| POST   | `/auth/login`    | Inicio de sesión y generación de JWT| ❌            |


### Tópicos
| Método | Endpoint          | Descripción                              | Autenticación |
|--------|-------------------|------------------------------------------|---------------|
| GET    | `/topics`         | Obtener todos los tópicos                | ✅            |
| POST   | `/topics`         | Crear un nuevo tópico                    | ✅            |
| PUT    | `/topics/{id}`    | Actualizar un tópico existente           | ✅            |
| DELETE | `/topics/{id}`    | Eliminar un tópico                       | ✅            |

## Reglas de negocio 

- Los usuarios no pueden crear tópicos a nombre de otro usuario
- Los usuarios no pueden editar tópicos creados por otro usuario
- Los usuarios no administradores no pueden eliminar tópicos creados por otro usuario

## Requisitos 

- Java SE Development Kit (Versión 21 o superior)
- Apache Maven (Versión 3.8.8 o superior)
- Postgres SQl (Versión 15.3 o superior)

## Preparación de la base de datos

Conexión al cliente de Postgres

`sudo -iu postgres; psql`

Creación de la base de datos

`create database oneg7; \c oneg7`

Creación del esquema de la aplicación

`create schema forohub`