### Clases
* `ProductController` Handler de los endpoints de producto.
* `AuthController` Handler con un unico endpoint para autenticar.
* `StaticController` Handler para ver los archivos estaticos
* `Product` modelo de un producto
* `SecurityConfig` Bean de configuracion de los filtros que permite al endpoint `/auth` ser llamado publicamente, pero los demas endpoints deben ser llamados con autenticacion.
* `TokenAuthenticationFilter` Bean que valida que el request contenga el header Authorization + Bearer token.
* `WebConfig` Bean de configuracion para manejar configuracion de archivos estaticos.
* `AuthService` Servicio donde deberia estar implementada la logica de buscar la informacion necesaria del usuario para construir el token real.
* `ProductService` Servicio para manejo de productos.
* `Application` punto de entrada para crear todos los beans en el contexto de Spring.

### Como corro el ejercicio?

Se disponibiliza un archivo Makefile con varios comando para que correr el ejercicio sea simlemente correr un comando user friendly.

Con correr solo `make run` es suficiente.

* `make run` -> hace el build del proyecto y levanta la imagen de docker
* `make d-stop` -> detiene el container
* `make d-clean` -> borra la imagen

### Como seguir el codigo y las pruebas?

Los 2 endpoints principales de `ProductController` estan autenticados, es decir, se debe enviar el token correcto(mockeado) para que funcione, sino devuelve 403 Forbidden.

1. POST /auth
    ```bash
   curl --location --request POST 'http://localhost:8080/auth' \
   --header 'Content-Type: application/json' \
   --data '{
   "username": "admin",
   "password": "admin"
   }'
2. POST /products
    ```bash
   curl --location --request POST 'http://localhost:8080/products' \
   --header 'Authorization: Bearer mock-token' \
   --header 'Content-Type: application/json' \
   --data '{
   "id": "e06406fe-e887-4e64-be56-8ec36c93fcb4",
   "name": "first"
   }'
3. GET /products
    ```bash
   curl --location 'http://localhost:8080/products' \
    --header 'Authorization: Bearer mock-token'


### Consideraciones

* Por simplicidad, el usuario y contraseña estan harcodeados en el application.yaml.
* Si bien no lo cree, podria haber manejado una lista de estado de creacion de los productos mientras se crean y crear un endpoint que consulte por ellos.
* Los archivos estaticos estan manejados desde un WebConfig para mayor facilidad de poder manejar en un unico lugar la configuracion de cada archivo.
* Por cuestion de tiempo y la poca logica que tenia el ejercicio, no implemente test unitarios.