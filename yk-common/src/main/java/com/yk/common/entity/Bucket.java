package com.yk.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zb-xxqg-platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-05-19 15:27
 **/
@Data
public class Bucket implements Serializable {
    private static final long serialVersionUID = 7876293024977658325L;

    //存储空间名称
    private String bucketName;
    //存储类型
    private String storageType;
    //数据容灾类型
    private String dataRedundancyType;
    //存储空间权限
    private String cannedACL;
}