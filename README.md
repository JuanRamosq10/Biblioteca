# Biblioteca
USUARIOS
Ruta -> http://localhost:8080/api/usuarios

CREAR USUARIOS
POST http://localhost:8080/api/usuarios
{
	"nombre" : "Juan",
   	 "email" : "juanrq@gmail.com",
    	"dni": "51501855W"
}
LISTAR USUARIOS
GET http://localhost:8080/api/usuarios

	OBTENER USUARIO POR ID
GET http://localhost:8080/api/usuarios/1

	ACTUALIZAR USUARIO
PUT http://localhost:8080/api/usuarios/1
{
	"nombre" : "Juan (Nuevo)",
   	 "email" : "juanrq10@gmail.com",
    	"dni": "51501855W"
}
	ELIMINAR USUARIO
DELETE http://localhost:8080/api/usuarios/1
 
LIBROS
Ruta -> http://localhost:8080/api/libros

CREAR LIBROS
POST http://localhost:8080/api/libros
{
	"titulo" : "Libro 1",
    	"autor" : "Andres",
    	"isbn" : "9788497592208",
    	"categoria" : "Ficcion",
    	"ejemplaresDisponibles" : 1
}
LISTAR LIBROS
GET http://localhost:8080/api/libros

	OBTENER LIBRO POR ID
GET http://localhost:8080/api/libros/1

	ACTUALIZAR LIBRO
PUT http://localhost:8080/api/libros/1
{
	"titulo" : "Libro 1 (Modificado)",
    	"autor" : "Andres ",
    	"isbn" : "9788497592208",
    	"categoria" : "Ficcion",
    	"ejemplaresDisponibles" : 5
}

	BUSCAR LIBRO POR TITULO
GET http://localhost:8080/api/libros/buscarTitulo?titulo=Libro

	BUSCAR LIBRO POR AUTOR
GET http://localhost:8080/api/libros/buscarAutor?autor=Andres

	ELIMINAR LIBRO
DELETE http://localhost:8080/api/libros/1
 
PRESTAMOS
Ruta -> http://localhost:8080/api/prestamos

CREAR PRESTAMOS
POST http://localhost:8080/api/prestamos?libroId=1&usuarioId=1

LISTAR PRESTAMOS
GET http://localhost:8080/api/prestamos

	OBTENER PRESTAMO POR ID
GET http://localhost:8080/api/prestamos/1

	DEVOLVER PRESTAMO
POST  http://localhost:8080/api/prestamos/1/devolver

	OBTENER PRESTAMOS POR USUARIO
GET  http://localhost:8080/api/prestamos/usuario/1

	OBTENER PRESTAMOS ACTIVOS
GET http://localhost:8080/api/prestamos/activos
