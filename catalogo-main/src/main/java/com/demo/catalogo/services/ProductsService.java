package com.demo.catalogo.services;

//Importacion de librerias necesarias para el servicio
import com.demo.catalogo.entities.ProductsEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //Me ayuda a inyectar esta clase en otros componentes como el controlador usanso el @Autowired
public class ProductsService {
    private List<ProductsEntity> products; //Me va a almacenar en una  lista los productos

    //Se crea un constructor de la clase ProductsService
    public ProductsService() {
        products = new ArrayList<>(); //Se inicializa la lista de productos conb el ArrayList
        //Se crea la lista de los Productos con la instacia products
        products.add(new ProductsEntity(UUID.randomUUID(), "Silla", "Muebles", 150000, 50));
        products.add(new ProductsEntity(UUID.randomUUID(), "Televisor", "Electrodomésticos", 2500000, 20));
        products.add(new ProductsEntity(UUID.randomUUID(), "Zapatillas", "Ropa", 500000, 100));
        products.add(new ProductsEntity(UUID.randomUUID(), "Cafetera", "Cocina", 200000, 35));
        products.add(new ProductsEntity(UUID.randomUUID(), "Auriculares", "Tecnología", 300000, 60));
    }

    // Metodo que devuelve la lista de todos los productos
    public List<ProductsEntity> getAllProducts() {
        return products; //Retorna la lista de los productos almacenados con la solicitud GET
    }

    // Metodo que devuelve el producto con un ID en especifico, filtrado por una consulta Lambda
    public Optional<ProductsEntity> getProductById(UUID id) {
        // Return que funciona primero convirtiendo la lista de productos en un flujo para realizar operacion
        // despues filtra el flujo con el ID especificado
        // luego devuelve el producto que coincida con el id, si no retorna un Optional vacio
        // Esto lo genera con una consulta GET
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    //Metodo que crea el producto y lo devuelve con un ID
    public UUID createProduct(ProductsEntity product) {
        product.setId(UUID.randomUUID()); //Genera un ID y se lo asigna al producto creado
        products.add(product); //Agrega el producto a la lista con la instacia products
        return product.getId(); //Devuelve el ID del producto creado
    }

    //Metodo que Actualiza el producto mediante una consulta lambda
    public Optional<ProductsEntity> updateProduct(UUID id, ProductsEntity updatedProduct) {
        Optional<ProductsEntity> existingProduct = getProductById(id); //Busca el producto por su ID
        existingProduct.ifPresent(product -> { //Si el ID es existente ejecuta el bloque de codigo
            product.setName(updatedProduct.getName()); //Actualiza el Nombre si es necesario
            product.setCategory(updatedProduct.getCategory()); //Actualiza la categoria si es necesario
            product.setPrice(updatedProduct.getPrice()); //Actualiza el precio si es necesario
            product.setStock(updatedProduct.getStock()); //Actualiza el Stock si es necesario
        });
        return existingProduct; //Retorna el producto ya actualizado
    }

    //Metodo que elimina un producto por su ID con una consulta lambda
    public boolean deleteProductById(UUID id) {
        return products.removeIf(product -> product.getId().equals(id)); //Devuelve un true si el producto se elimino
    }
}
