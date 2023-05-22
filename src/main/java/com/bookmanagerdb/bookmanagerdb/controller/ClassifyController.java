package com.bookmanagerdb.bookmanagerdb.controller;

import com.bookmanagerdb.bookmanagerdb.config.CurrentAuth;
import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.dao.ClassifyRepository;
import com.bookmanagerdb.bookmanagerdb.entity.Classify;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.ClassifyDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classify")
public class ClassifyController {
    @Autowired
    ClassifyRepository classifyRepository;

    @ResponseBody
    @GetMapping("/allClassify")
    public List<Classify> selectAllClassify() throws Exception{
        return classifyRepository.findAll();
    }

    @PostMapping("/addClassify")
    public Classify addClassify(@RequestBody @NotNull ClassifyDTO classifyDTO, CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if(RoleConstants.BOOK_ADMIN.equals(user.getType())){
            Classify classify = new Classify(
                    classifyDTO.getId(),
                    classifyDTO.getClassification()
            );
            return classifyRepository.save(classify);
        }else throw new Exception("没有权限");
    }

    @PutMapping("/updateClassify")
    @ResponseBody
    public Classify updateClassify(@RequestBody Classify classify, CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if(RoleConstants.BOOK_ADMIN.equals(user.getType())){
            classifyRepository.save(classify);
            return classify;
        }else throw new Exception("没有权限");
    }

    @DeleteMapping("/deleteClassify")
    public String deleteClassify(@RequestParam("id") @NonNull String id, CurrentAuth currentAuth) throws Exception{
        User user = currentAuth.getUser();
        if(RoleConstants.BOOK_ADMIN.equals(user.getType())){
            classifyRepository.deleteById(id);
            return ("删除分类成功");
        }else throw new Exception("没有权限");
    }
}
