package com.bookmanagerdb.bookmanagerdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Borrow)实体类
 *
 * @author makejava
 * @since 2023-04-12 20:39:35
 */
@Entity
@Table(name = "borrow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow implements Serializable {
    private static final long serialVersionUID = 410662925034550243L;
    /**
     * 借阅编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 图书号
     */
    @Column(name = "book_id")
    private String bookId;
    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;
    /**
     * 借出时间
     */
    @Column(name = "borrow_time")
    private Date borrowTime;
    /**
     * 归还时间
     */
    @Column(name = "return_time")
    private Date returnTime;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 分类id
     */
    @Column(name = "classification_id")
    private Integer classificationId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

}

