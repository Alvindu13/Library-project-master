package com.clientui.web.controller;

import com.clientui.beans.BookBean;
import com.clientui.web.proxies.MicroserviceBookProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    MicroserviceBookProxy mBookProxy;


    @GetMapping
    public String allBooks(Model model){

        List<BookBean> books =  mBookProxy.findBooks();
        model.addAttribute("books", books);

        return "listBooks";
    }

    @GetMapping("/user/{borrowerId}")
    public String borrowerBooks(@PathVariable Long borrowerId, Model model) {

        List<BookBean> borrowBooks = mBookProxy.borrowerBooks(borrowerId);

        model.addAttribute("borrowBooks", borrowBooks);

        return "listBorrowerBooks";
    }
}
