package com.demo.catalogo.entities;


import lombok.*;
import java.util.UUID;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 

//Declaracion de la clase que me representa la entidad de un producto
public class ProductsEntity {
    private UUID id; 
    private String name; 
    private String category; 
    private double price; 
    private int stock; 
}

