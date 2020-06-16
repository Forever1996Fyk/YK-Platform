package com.yk.common.enums;

import com.aliyun.oss.model.CannedAccessControlList;
import lombok.Getter;

/**
 * @program: yk-platform
 * @description: 存储空间权限
 * @author: YuKai Fan
 * @create: 2020-06-16
 **/
@Getter
public enum CannedAccessControlEnum {
    ACL_READ_WRITE("PubRW", CannedAccessControlList.PublicReadWrite),//公共读写
    ACL_READ("PubR", CannedAccessControlList.PublicRead), //公共读
    ACL_PRIVATE("Private", CannedAccessControlList.Private), //私有
    ;

    private String type;
    private CannedAccessControlList cannedAccessControlList;

    CannedAccessControlEnum(String type, CannedAccessControlList cannedAccessControlList) {
        this.type = type;
        this.cannedAccessControlList = cannedAccessControlList;
    }

    public static CannedAccessControlList getCannedAccessControlListByType(String type) {
        CannedAccessControlEnum[] cannedAccessControlEnums = values();
        for (CannedAccessControlEnum cannedAccessControlEnum : cannedAccessControlEnums) {
            if (cannedAccessControlEnum.getType().equals(type)) {
                return cannedAccessControlEnum.getCannedAccessControlList();
            }
        }
        return ACL_READ.getCannedAccessControlList();
    }
}