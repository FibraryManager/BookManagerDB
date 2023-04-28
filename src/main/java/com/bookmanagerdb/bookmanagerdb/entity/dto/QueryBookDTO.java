package com.bookmanagerdb.bookmanagerdb.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class QueryBookDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5951841866179438422L;

    private String bookName;

    /**
     * 分类id
     */
    private Integer classificationId;


}
