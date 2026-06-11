package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired   //inject the dependency without using new
    private BookRepository bRepo;

    public List<Book> getAllBooks() {
        return bRepo.findAll();
    }

    public void save(Book b){
        bRepo.save(b);  //b is a object
    }

    public Book getBookById(Integer id){
        return bRepo.findById(id).get();
    }

    public void deleteById(Integer id){
        bRepo.deleteById(id);
    }
}
