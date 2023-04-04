package com.bookmanagerdb.bookmanagerdb.utils;

import com.alibaba.fastjson2.JSON;

import javax.persistence.AttributeConverter;

public class ListJsonConverter implements AttributeConverter<Object, String> {
    @Override
    public String convertToDatabaseColumn(Object o) {
        return JSON.toJSONString(o);
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        return JSON.parseArray(s);
    }
}
