package com.bookmanagerdb.bookmanagerdb.services;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.util.Map;

public interface FileService {

    /**
     * 多文件上传
     * @param file
     * @return
     * @throws QiniuException
     */
    Map uploadFile(File file) throws QiniuException;
}
