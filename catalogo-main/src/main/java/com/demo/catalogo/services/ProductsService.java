package com.demo.catalogo.services;

¿
import com.demo.catalogo.entities.ProductsEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class ProductsService {
    private List<ProductsEntity> products; 

    //Se crea un constructor de la clase ProductsService
    public ProductsService() {
        products = new ArrayList<>();
        //Se crea la lista de los Productos con la instacia products
        products.add(new ProductsEntity(UUID.randomUUID(), "Silla", "Muebles", 150000, 50));
        products.add(new ProductsEntity(UUID.randomUUID(), "Televisor", "Electrodomésticos", 2500000, 20));
        products.add(new ProductsEntity(UUID.randomUUID(), "Zapatillas", "Ropa", 500000, 100));
        products.add(new ProductsEntity(UUID.randomUUID(), "Cafetera", "Cocina", 200000, 35));
        products.add(new ProductsEntity(UUID.randomUUID(), "Auriculares", "Tecnología", 300000, 60));
    }

    // Metodo que devuelve la lista de todos los productos
    public List<ProductsEntity> getAllProducts() {
        return products;
    }

    // Metodo que devuelve el producto con un ID en especifico, filtrado por una consulta Lambda
    public Optional<ProductsEntity> getProductById(UUID id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    //Metodo que crea el producto y lo devuelve con un ID
    public UUID createProduct(ProductsEntity product) {
        product.setId(UUID.randomUUID()); 
        products.add(product);
        return product.getId();
    }

    //Metodo que Actualiza el producto mediante una consulta lambda
    public Optional<ProductsEntity> updateProduct(UUID id, ProductsEntity updatedProduct) {
    Optional<ProductsEntity> existingProductOptional = getProductById(id); // Busca el producto por su ID
    
    // Si el producto existe, actualiza sus campos
    existingProductOptional.ifPresent(existingProduct -> {
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName()); // Actualiza el nombre si es necesario
        }
        if (updatedProduct.getCategory() != null) {
            existingProduct.setCategory(updatedProduct.getCategory()); // Actualiza la categoría si es necesario
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice()); // Actualiza el precio si es necesario
        }
        if (updatedProduct.getStock() != null) {
            existingProduct.setStock(updatedProduct.getStock()); // Actualiza el stock si es necesario
        }
    });

    return existingProductOptional; // Retorna el producto, ya actualizado o vacío si no se encontró
}


    //Metodo que elimina un producto por su ID con una consulta lambda
    public boolean deleteProductById(UUID id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
