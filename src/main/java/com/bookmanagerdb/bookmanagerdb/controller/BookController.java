package com.bookmanagerdb.bookmanagerdb.controller;

import com.bookmanagerdb.bookmanagerdb.config.CurrentAuth;
import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.dao.BookRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.Borrow;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    /**
     * 管理员获取全部书籍
     */
    @ResponseBody
    @GetMapping("/allbooks")
    public List<Book> selectAllBorrows()throws Exception{

            return bookRepository.findAll();

    }
    /**
     * 上架书籍
     */
    @PostMapping("/addBook")
    public Book addBook(@RequestBody @NonNull BookDTO bookDTO,CurrentAuth currentAuth)throws Exception{
        User user = currentAuth.getUser();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            Book book = new Book(
                    bookDTO.getId(),
                    bookDTO.getIsbn(),
                    bookDTO.getBookName(),
                    bookDTO.getStatus(),
                    bookDTO.getClassificationId(),
                    bookDTO.getBookAuthor(),
                    bookDTO.getPublisher(),
                    bookDTO.getPbTime(),
                    bookDTO.getTime()
            );
            return bookRepository.save(book);
        }else throw new Exception("没有权限");

    }
    /**
     * 修改图书
     */
//    @PutMapping("/updateBook")
    @PostMapping("/updateBook")
    @ResponseBody
    public Book updateById(@RequestBody Book book, CurrentAuth currentAuth) throws Exception {
        User user = currentAuth.getUser();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            bookRepository.save(book);
           return book;
        }else throw new Exception("当前用户不是管理员！");
    }
    /**
     * 下架图书，如果书籍已被借出，则不能下架
     */
    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") @NonNull String id,CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            Book book = bookRepository.findById(id).get();
            int status = book.getStatus();
            if (status == 0){
                bookRepository.deleteById(id);
                return ("书籍下架成功");
            }else {
                throw new Exception ("当前书籍处于被借阅状态");
            }
        }else throw new Exception("没有权限");
    }
    /**
     * 图书详细信息
     */
    @ResponseBody
    @GetMapping("/requestBook")
    public Book requestBook(@RequestParam("id") @NonNull String id){
        Book book =  bookRepository.findById(id).get();
        return book;
    }

}
