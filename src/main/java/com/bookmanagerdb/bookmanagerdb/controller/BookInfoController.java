package com.bookmanagerdb.bookmanagerdb.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bookmanagerdb.bookmanagerdb.controller.ex.FileEmptyException;
import com.bookmanagerdb.bookmanagerdb.controller.ex.FileSizeException;
import com.bookmanagerdb.bookmanagerdb.controller.ex.FileUpLoadException;
import com.bookmanagerdb.bookmanagerdb.dao.BorrowRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.QueryBookDTO;
import com.bookmanagerdb.bookmanagerdb.services.BookService;
import com.bookmanagerdb.bookmanagerdb.utils.JsonResult;
import com.bookmanagerdb.bookmanagerdb.utils.UploadGiteeImgBed;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@CrossOrigin
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
    public JsonResult<Boolean> report(@RequestBody MultipartFile file,@RequestParam("id") @NonNull Integer id){
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

    /**
     *  上传图片
     * @param multipartFile 文件对象
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImg")
    public Object uploadImg(@RequestParam("file")MultipartFile multipartFile, @RequestParam("id") @NonNull Integer id) throws IOException {
        //根据文件名生成指定的请求url
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null){
            throw new RuntimeException("图片信息为空");
        }
        System.out.println("图片名称:" + originalFilename);
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
        System.out.println("目标url："+targetURL);
        //请求体封装
        Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile.getBytes());
        //借助HttpUtil工具类发送POST请求
        String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
        //解析响应JSON字符串
        JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
        //请求失败
        if(jsonObj == null || jsonObj.getObj("commit") == null){
            return "请求失败";
        }
        //请求成功：返回下载地址
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
        System.out.println(content);
        bookService.updateImgUrl(content.getObj("name").toString(),id);
        return content.getObj("name");
    }
}
