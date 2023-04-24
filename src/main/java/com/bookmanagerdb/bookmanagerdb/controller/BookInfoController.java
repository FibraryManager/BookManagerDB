package com.bookmanagerdb.bookmanagerdb.controller;

import com.bookmanagerdb.bookmanagerdb.dao.BorrowRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.dto.QueryBookDTO;
import com.bookmanagerdb.bookmanagerdb.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book/ChangeInfo")
public class BookInfoController {

    @Autowired
    BorrowRepository InfoChangeRepository;

    @Resource
    private BookService bookService;

    @GetMapping("/list")
    public List<Book> queryBookList(@RequestBody QueryBookDTO queryBookDTO){
        return bookService.query(queryBookDTO.getBookName(), queryBookDTO.getClassificationId());
    }

}
