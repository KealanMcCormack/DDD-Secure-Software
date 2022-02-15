package com.example.controller;

import com.example.exception.BookNotFoundException;
import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // See All Books on Homepage
    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "welcome";
    }

    // Delete a Book
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
        return viewHomePage(model);
    }

    // Create a Book
    @RequestMapping("/new")
    public String createBook(){
        return "bookform";
    }
    // Save Created Book
    @PostMapping("/books")
    public String saveCreatedBook(@ModelAttribute("book") Book book, Model model){
        bookRepository.save(book);
        return viewHomePage(model);
    }

    // Update a Book
    // Get Book By ID and open the editform
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable(value="id") Long bookId, Model model) throws BookNotFoundException{
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);
        return "editform";
    }
    // Save Updated Details
    @RequestMapping(value="/books/save", method=RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book, Model model){
        bookRepository.save(book);
        return viewHomePage(model);
    }
}
