package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.service.BookService;
import com.bookStore.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")

    public String home(){
        return "home";
    }

    @GetMapping("/book_register")

    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("/available_books")

    public ModelAndView getAllBook(){
        List<Book> list = service.getAllBooks();
//        ModelAndView m = new ModelAndView();
//        m.setViewName("availableBooks");
//        m.addObject("book", list);   //insted of three lines we add one line parameterized constructor

        return new ModelAndView("availableBooks", "book", list);
    }

//        @GetMapping("/availableBooks")   // ← must match exactly
//        public String showBooks(Model model) {
//            model.addAttribute("book", service.getAllBooks());
//            return "availableBooks";  // matches availableBooks.html in templates/
//        }

    @PostMapping("/save")

    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")

    public String getMyBooks(Model model){
        List<MyBookList> list = myBookService.getAllMyBooks();
        model.addAttribute("book", list);

        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") Integer id) {
        Book b = service.getBookById(id);
        MyBookList mb = new MyBookList(null, b.getName(), b.getAuthor(), b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book", b);

        return "bookEdit";  //html file path
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        service.deleteById(id);
        return "redirect:/available_books";
    }
}




