package com.bookmanagerdb.bookmanagerdb.controller;

import com.bookmanagerdb.bookmanagerdb.controller.ex.FileEmptyException;
import com.bookmanagerdb.bookmanagerdb.controller.ex.FileSizeException;
import com.bookmanagerdb.bookmanagerdb.controller.ex.FileUpLoadException;
import com.bookmanagerdb.bookmanagerdb.dao.BorrowRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.QueryBookDTO;
import com.bookmanagerdb.bookmanagerdb.services.BookService;
import com.bookmanagerdb.bookmanagerdb.utils.JsonResult;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("/book/ChangeInfo")
public class BookInfoController {

    @Autowired
    BorrowRepository InfoChangeRepository;

    @Resource
    private BookService bookService;
/*
*
*
* 查询接口*/
    @GetMapping("/list")
    public List<Book> queryBookList(@RequestBody QueryBookDTO queryBookDTO){
        return bookService.query(queryBookDTO.getBookName(), queryBookDTO.getClassificationId());
    }



    /*
    图片上传接口
    * */
    @PostMapping("/img")
    public JsonResult<Boolean> report( MultipartFile file,Integer id,HttpSession session){
        long startTime = System.currentTimeMillis();
        String fileName = startTime + file.getOriginalFilename();
//        String src = "/file/";
        String src = "https://raw.githubusercontent.com/yunduo23/picgoPic/";
        File dir = new File(src);
        if(!dir.exists()){
            boolean mkdir = dir.mkdir();//不存在即创建
            if (!mkdir){
                throw new RuntimeException("创建文件夹失败");
            }
        }
        File file1 = new File(dir, fileName); //文件路径
        String img = src +"\\"+ fileName;
        try {
            file.transferTo(file1);
            bookService.updateImgUrl(img,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        return new JsonResult<>(200,true);
    }
}
