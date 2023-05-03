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
public class BorrowDTO {

    private String id;
    private String bookId;
    private String bookName;
    private Date borrowTime;
    private Date returnTime;
    private Integer classificationId;
}
