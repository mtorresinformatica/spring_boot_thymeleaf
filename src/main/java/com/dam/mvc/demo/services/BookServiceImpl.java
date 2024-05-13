package com.dam.mvc.demo.services;

import com.dam.mvc.demo.entities.Book;
import com.dam.mvc.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
