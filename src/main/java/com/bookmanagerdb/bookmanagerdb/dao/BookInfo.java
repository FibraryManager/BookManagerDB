package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Book;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface BookInfo extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
/*
* 数据库模糊搜索*/
    @Query(value = "select * from book where (book_name like CONCAT('%',?1,'%') or ?1 IS NULL ) " +
            "and (classification_id = ?2 or ?2 IS NULL)", nativeQuery = true)
    List<Book> queryByBookNameContainingOrClassificationIdContaining(@Param("bookName")String bookName, @Param("classificationId")Integer classificationId);
    /*
    * 图片上传绑定数据库*/
    @Modifying
    @Query(value = "update book b set b.imageUrl =?1 where b.id = ?2",nativeQuery = true)
    void updateImgUrl( String imageUrl, Integer id);
//    List<Book> queryByBookNameContainingOrClassificationIdContaining(String bookName, Integer classificationId);

//    List<Book> queryByBookNameContaining(String bookName);

}
