package com.bookmanagerdb.bookmanagerdb.controller;

import com.bookmanagerdb.bookmanagerdb.config.CurrentAuth;
import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.dao.BookRepository;
import com.bookmanagerdb.bookmanagerdb.dao.BorrowRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.Borrow;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.BorrowDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 借阅管理
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BookRepository bookRepository;


    /**
     * 获取全部借阅记录
     */
    @ResponseBody
    @GetMapping("/allBorrows")
    public List<Borrow> selectAllBorrows(CurrentAuth currentAuth)throws Exception{
       User user = currentAuth.getUser();
       if (RoleConstants.BOOK_ADMIN.equals(user.getType()))
            return borrowRepository.findAll();
       else throw new Exception("当前用户没有权限");
    }
    /**
     * 读者获取个人借阅
     */
    @ResponseBody
    @GetMapping("/reader/allBooks")
    public List<Borrow> selectMyAll(CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        System.out.printf("sss_"+user);
        List<Borrow> borrowList = borrowRepository.findBorrowByUserId(user.getUserId());
        return borrowList;
    }

    /**
     * 读者借书
     */
    @ResponseBody
    @GetMapping("/lendBook")
    public String lendBook(@RequestParam("id")@Nullable String id,CurrentAuth currentAuth) throws Exception{
        //id：book的id
        System.out.println(id);
        Book book = bookRepository.findById(id).get();
        Integer status = book.getStatus();
        System.out.println(status);
        if (status == 0){
            //可以借阅
            Borrow borrow = new Borrow();
            borrow.setBookId(id);
            borrow.setBookName(book.getBookName());
            Date timeD = new Date();
            borrow.setBorrowTime(timeD);
            /**
             *  timeD:指定日期
             * 1：获取指定日期的后面1个月
             * date：最终需要的日期
             */
//            Date startData = new SimpleDateFormat("yyyy-MM-dd").parse(timeD);
            LocalDateTime localDateTime = timeD.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
                    .plusMonths(1);
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            borrow.setReturnTime(date);
            User user = currentAuth.getUser();
            if (user==null)
                return "请先登录！";
            borrow.setUserId(user.getUserId());
            borrow.setNickname(user.getNickname());
            borrow.setClassificationId(book.getClassificationId());
            borrowRepository.save(borrow);
            //借阅成功后修改书籍状态
            book.setStatus(1);//状态改为1

            bookRepository.save(book);
            return "借阅成功";
        }else if (status == 1){
            return "当前书籍已被借阅";
        }else throw new Exception("其他原因借阅失败！");
    }
    /**
     * 读者还书
     */
    @DeleteMapping("/returnBook")
    public String returnBook(@RequestParam("id")@NonNull  String id) throws Exception{
//        Borrow borrow = borrowRepository.findBorrowByUserId(userId);
        Borrow borrow = borrowRepository.findById(id).get();
        if (borrow != null){
            //判断是否超期
            Date date = borrow.getReturnTime();
            Date now;
            Calendar c = Calendar.getInstance();
            now = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
            if (now.before(date)){
                throw new Exception("已超过归还时间，请联系管理员！");
            }else {
                //没有超期，归还图书
                //删除借阅表记录
                borrowRepository.deleteById(id);
                String bookId = borrow.getBookId();
               Book book =  bookRepository.findById(bookId).get();
               book.setStatus(0);
               bookRepository.save(book);
               return ("归还成功");
            }
        }else throw new Exception("系统错误");
    }
    /**
     * 图书管理员还书
     */
    @DeleteMapping("/bookAdmin/returnBorrow")
    public String returnBookAdmin(@RequestParam("id") @NonNull String id,CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            Borrow borrow = borrowRepository.findById(id).get();
            if (borrow == null){
                throw new Exception("借阅记录不存在！");
            }else {
                borrowRepository.deleteById(id);
                String bookId = borrow.getBookId();
                Book book =  bookRepository.findById(bookId).get();
                book.setStatus(0);
                bookRepository.save(book);
                return ("归还成功");
            }
        } else throw new Exception("当前用户没有权限");
    }

    /**
     * 图书管理员删除借阅记录
     */
    @DeleteMapping("/deleteBorrow")
    public String deleteById(@RequestParam("id") @NonNull String id,CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            Borrow borrow = borrowRepository.findById(id).get();
            if (borrow == null){
                throw new Exception("借阅记录不存在！");
            }else {
                borrowRepository.deleteById(id);
                String bookId = borrow.getBookId();
                Book book =  bookRepository.findById(bookId).get();
                book.setStatus(0);
                bookRepository.save(book);
                return "删除成功！";
            }
        } else throw new Exception("当前用户没有权限");
    }
}
