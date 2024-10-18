package com.demo.catalogo.controllers;


import com.demo.catalogo.entities.ProductsEntity;
import com.demo.catalogo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/1.0/products")
public class ProductsController {

    
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    //Metodo que devuelve una ResponseEntity con la lista de productos
    public ResponseEntity<List<ProductsEntity>> getProducts() {
        List<ProductsEntity> products = productsService.getAllProducts(); 
        return ResponseEntity.ok(products); 
    }

    @GetMapping("/{id}") 
    // Metodo que devuelve una ResponseEntity con un ID en especifico
    public ResponseEntity<ProductsEntity> getProduct(@PathVariable UUID id) {
        Optional<ProductsEntity> product = productsService.getProductById(id);
        return product.map(ResponseEntity::ok) 
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @PostMapping
// Método para crear un producto
public ResponseEntity<String> createProduct(@RequestBody ProductsEntity product) {
    try {
        UUID createdProductId = productsService.createProduct(product); // Se llama al servicio para crear un producto y se obtiene un nuevo ID
        return ResponseEntity.created(URI.create("/api/1.0/products/" + createdProductId)).body("Producto creado correctamente"); // Crea una respuesta 201 (Created) con la ubicación del producto
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el producto: " + e.getMessage()); // Maneja posibles errores y retorna 400 (Bad Request)
    }
}


    @PutMapping("/{id}") // 
// Método para actualizar un producto
public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductsEntity product) {
    Optional<ProductsEntity> updatedProduct = productsService.updateProduct(id, product);

    if (updatedProduct.isPresent()) {
        return ResponseEntity.ok().body("Producto actualizado correctamente");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado"); 
    }
}


    @DeleteMapping("/{id}") 
    //Metodo para eliminar un producto
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productsService.deleteProductById(id); 
        return ResponseEntity.ok("Producto Eliminado Correctamente"); 

}
