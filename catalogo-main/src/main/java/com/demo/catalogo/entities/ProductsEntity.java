package com.demo.catalogo.entities;

//Impotacion de librerias necesarias para la Entidad
import lombok.*;
import java.util.UUID;

@Data //Me genera los metodos Getter and Setter automaticamente
@NoArgsConstructor // Me genera un constructor sin argumentos
@AllArgsConstructor //Me genera un contructor con todos los parametros de la clase

//Declaracion de la clase que me representa la entidad de un producto
public class ProductsEntity {
    private UUID id; //Atributo que representa el identificador unico del producto
    private String name; //Atributo que representa el nombre de un producto
    private String category; //Atributo que representa la categoria de un producto
    private double price; //Atributo que representa el precio de un producto
    private int stock; //Atributo que representa la cantidad de productos disponibles
}

