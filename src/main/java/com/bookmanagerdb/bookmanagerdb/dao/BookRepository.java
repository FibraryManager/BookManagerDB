package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Book;
import com.bookmanagerdb.bookmanagerdb.entity.Borrow;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
//    @Transactional // 数据修改和删除，需要显示声明事务 ,直接在接口方法上添加注解 或者在调用的方法上添加注解
//    @Modifying
//    @Query("update Book s set s.status =:status where s.id=:id")
//    void updateBook(String name, String id);// 注意:定义更新方法 返回值只能是void或者int,不然会报错

}
