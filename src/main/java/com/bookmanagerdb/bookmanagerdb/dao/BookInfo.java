package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInfo extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

    @Query(value = "select * from book where (book_name like CONCAT('%',?1,'%') or ?1 IS NULL ) " +
            "and (classification_id = ?2 or ?2 IS NULL)", nativeQuery = true)
    List<Book> queryByBookNameContainingOrClassificationIdContaining(@Param("bookName")String bookName, @Param("classificationId")Integer classificationId);


//    List<Book> queryByBookNameContainingOrClassificationIdContaining(String bookName, Integer classificationId);

//    List<Book> queryByBookNameContaining(String bookName);

}
