package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Borrow;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.BorrowDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, String>, JpaSpecificationExecutor<Borrow> {

    List<Borrow> findBorrowByUserId(Long userId);

    //    @Select("select id, book_id,book_name,borrow_time,return_time,classification_id from borrow where user_id=#{userId}")
////   @Select("select * from borrow")
//    List<BorrowDTO> findMyBorrows(Long userId);
//    @Query(value = "select id,book_id,book_name,borrow_time,return_time,classification_id from borrow where user_id =?1",nativeQuery = true)
//    List<BorrowDTO> findMyBorrowByUserId(@Param("userId") Long userId);
}
