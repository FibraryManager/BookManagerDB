package com.bookmanagerdb.bookmanagerdb.services;

import com.bookmanagerdb.bookmanagerdb.dao.BookInfo;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookInfo bookInfo;

    public List<Book> query(String bookName, Integer classificationId){
        List<Book> bookList = bookInfo.queryByBookNameContainingOrClassificationIdContaining(bookName, classificationId);
        return bookList;
    }
}
