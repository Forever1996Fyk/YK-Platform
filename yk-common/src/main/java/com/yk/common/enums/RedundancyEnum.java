package com.yk.common.enums;

import com.aliyun.oss.model.DataRedundancyType;
import lombok.Getter;

/**
 * @program: yk-platform
 * @description: 容灾类型
 * @author: YuKai Fan
 * @create: 2020-06-16
 **/
@Getter
public enum RedundancyEnum {
    REDUNDANCY_LOCAL("Local", DataRedundancyType.LRS), //本地冗余
    REDUNDANCY_REGION("Region", DataRedundancyType.ZRS), //同城冗余
    ;
    private String type;
    private DataRedundancyType dataRedundancyType;

    RedundancyEnum(String type, DataRedundancyType dataRedundancyType) {
        this.type = type;
        this.dataRedundancyType = dataRedundancyType;
    }

    public static DataRedundancyType getDataRedundancyTypeByType(String type) {
        RedundancyEnum[] redundancyEnums = values();
        for (RedundancyEnum redundancyEnum : redundancyEnums) {
            if (redundancyEnum.getType().equals(type)) {
                return redundancyEnum.getDataRedundancyType();
            }
        }
        return REDUNDANCY_LOCAL.getDataRedundancyType();
    }
}