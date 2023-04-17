package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Borrow;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, String>, JpaSpecificationExecutor<Borrow> {

    List<Borrow> findBorrowByUserId(Long userId);
}
