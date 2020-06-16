package com.yk.common.enums;

import com.aliyun.oss.model.StorageClass;
import lombok.Getter;

/**
 * @program: yk-platform
 * @description: 存储类型
 * @author: YuKai Fan
 * @create: 2020-06-16
 **/
@Getter
public enum StorageClassEnum {
    STORAGE_STANDARD("Standard", StorageClass.Standard), //标准
    STORAGE_INFREQUENT_ACCESS("InfrequentAccess", StorageClass.IA), //低频访问
    STORAGE_ARCHIVE("Archive", StorageClass.Archive), //归档
    ;
    private String type;
    private StorageClass storageClass;

    StorageClassEnum(String type, StorageClass storageClass) {
        this.type = type;
        this.storageClass = storageClass;
    }

    public static StorageClass getStorageClassByType(String type) {
        StorageClassEnum[] storageClassEnums = values();
        for (StorageClassEnum storageClassEnum : storageClassEnums) {
            if (storageClassEnum.getType().equals(type)) {
                return storageClassEnum.getStorageClass();
            }
        }
        return STORAGE_STANDARD.getStorageClass();
    }
}