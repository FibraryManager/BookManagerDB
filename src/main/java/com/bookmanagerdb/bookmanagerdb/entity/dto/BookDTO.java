package com.bookmanagerdb.bookmanagerdb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String id;

    private String isbn;

    private String bookName;

    private Integer status;

    private Integer classificationId;

    private String bookAuthor;

    private String publisher;

    private Date pbTime;

    private Date time;
    private String imageUrl;
}
