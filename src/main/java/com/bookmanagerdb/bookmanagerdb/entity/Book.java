package com.bookmanagerdb.bookmanagerdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Book)实体类
 *
 * @author makejava
 * @since 2023-04-12 21:03:23
 */
@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = -29576697867514509L;
    /**
     * 图书号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 图书编号
     */
    @Column(name = "isbn")
    private String isbn;
    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;
    /**
     * 出借状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 分类id
     */
    @Column(name = "classification_id")
    private Integer classificationId;
    /**
     * 作者姓名
     */
    @Column(name = "book_author")
    private String bookAuthor;
    /**
     * 出版社
     */
    @Column(name = "publisher")
    private String publisher;
    /**
     * 出版时间
     */
    @Column(name = "pb_time")
    private Date pbTime;
    /**
     * 入库时间
     */
    @Column(name = "time")
    private Date time;
    /**
     * 图书图片
     */
    @Column(name = "image_url")
    private String imageUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPbTime() {
        return pbTime;
    }

    public void setPbTime(Date pbTime) {
        this.pbTime = pbTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}

