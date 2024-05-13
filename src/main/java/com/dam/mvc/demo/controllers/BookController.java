package com.dam.mvc.demo.controllers;

import com.dam.mvc.demo.entities.Book;
import com.dam.mvc.demo.services.BookService;
import com.dam.mvc.demo.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(){
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String findAll(Model model){
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/books/view/{id}")
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("book", bookService.findById(id).get());
        return "book-view";
    }

    @GetMapping("/books/form")
    public String getEmptyForm(Model model){
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @GetMapping("/books/edit/{id}")
    public String getFormWithBook(Model model, @PathVariable Long id){
        if(bookService.existsById(id)) {
            bookService.findById(id).ifPresent(b -> model.addAttribute("book", b));
            return "book-form";
        } else {
            return "redirect:/books/form";
        }

    }

    @PostMapping("/books")
    public String create(@ModelAttribute Book book){
        if(book.getId() != null){
            // actualización
            bookService.findById(book.getId()).ifPresent(b -> {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                bookService.save(b);
            });
        } else{
            // creación
            bookService.save(book);
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteById(@PathVariable Long id){
        if(bookService.existsById(id))
            bookService.deleteById(id);
        return "redirect:/books";
    }



}

