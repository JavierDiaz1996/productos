package com.demo.catalogo.controllers;

//Importacion de librerias necesarias para el controlador
import com.demo.catalogo.entities.ProductsEntity;
import com.demo.catalogo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Gestiona las solicitudes HTTP y las devuelve en respuestas de formato JSON
@RestController
//Defino la ruta base para asociar con las solicitudes HTTP
@RequestMapping("/api/1.0/products")
public class ProductsController {

    //Se almacena la instancia productService para utilizarla en metodos del controlodador
    private final ProductsService productsService;

    //Implementa la inyeccion de dependencias en la instacia de ProductsService
    @Autowired
    //Constructor que recibe la instancia de productsService y se asigna a productsService
    public ProductsController(ProductsService productsService) {
        //Permite que el controlador use los metodos de ProductsService
        //en otros metodos del controlador para realizar operaciones
        this.productsService = productsService;
    }


    @GetMapping //Maneja solicitudes GET
    //Metodo que devuelve una ResponseEntity con la lista de productos
    public ResponseEntity<List<ProductsEntity>> getProducts() {
        List<ProductsEntity> products = productsService.getAllProducts(); //Genero un llamdo de toda la lista de productos
        return ResponseEntity.ok(products); // Retorna una respuesta HTTP 200 (OK) con la lista de productos en el cuerpo de la respuesta
    }

    @GetMapping("/{id}") //Maneja solicitudes GET segun su ID
    // Metodo que devuelve una ResponseEntity con un ID en especifico
    public ResponseEntity<ProductsEntity> getProduct(@PathVariable UUID id) {
        Optional<ProductsEntity> product = productsService.getProductById(id); // Se llama al servicio para que busque el producto con su ID
        return product.map(ResponseEntity::ok) //Si el producto se encuentra, retorna una respuesta HTTP 200 (OK) con el producto en el cuerpo de la respuesta
                .orElseGet(() -> ResponseEntity.notFound().build()); //Si el producto no se encuentra, retorna un error 404 (Not Found)
    }

    @PostMapping //Maneja solicitudes POST
    //Metodo para crear un producto
    public ResponseEntity<String> createProduct(@RequestBody ProductsEntity product) {
        UUID createdProductId = productsService.createProduct(product); //Se llama al servicio para crear un producto y se obtiene un nuevo ID
        return ResponseEntity.created(URI.create("/api/1.0/products/" + createdProductId)).body("Producto Creado Correctamente"); //Crea una respuesta 201 (create) con la ubicacion del producto
    }

    @PutMapping("/{id}") // Maneja Solicitudes PUT segun su ID
    //Metodo para actualizar un producto
    public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductsEntity product) {
        Optional<ProductsEntity> updatedProduct = productsService.updateProduct(id, product); //Se llama al servicio para actualizar un producto segun el ID especificado
        return ResponseEntity.ok().body("Producto Actualizado Correctamente"); // Retorna una respuesta HTTP 200 (OK) con un mensaje de éxito en el cuerpo de la respuesta
    }

    @DeleteMapping("/{id}") //Maneja solicitudes DELETE segun su ID
    //Metodo para eliminar un producto
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productsService.deleteProductById(id); // Se llama al Servicio para eliminar un producto segun el ID especificado
        return ResponseEntity.ok("Producto Eliminado Correctamente"); // Retorna una respuesta HTTP 200 (OK) con un mensaje de éxito en el cuerpo de la respuesta
    }

}
