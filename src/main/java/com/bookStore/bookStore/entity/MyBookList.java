package com.bookStore.bookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                       //it says this class create a table
@Table(name = "MyBooks")   //Hibernate create table in db MyBooks
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private String price;
}
