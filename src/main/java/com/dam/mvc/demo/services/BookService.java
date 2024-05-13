package com.dam.mvc.demo.services;

import com.dam.mvc.demo.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save (Book product);
    Boolean existsById(Long id);
    void deleteById(Long id);
}
